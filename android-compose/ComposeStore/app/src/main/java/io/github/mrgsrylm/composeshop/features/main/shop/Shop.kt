package io.github.mrgsrylm.composeshop.features.main.shop

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import io.github.mrgsrylm.composeshop.common.FeedbackType
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.features.main.MainViewModel
import io.github.mrgsrylm.composeshop.features.main.shop.components.CategoryProduct
import io.github.mrgsrylm.composeshop.features.main.shop.components.ProductCategory
import io.github.mrgsrylm.composeshop.features.main.shop.core.CategoryEvent
import io.github.mrgsrylm.composeshop.features.product.AddToCartActivity
import io.github.mrgsrylm.composeshop.features.product.common.Constants
import io.github.mrgsrylm.composeshop.features.product.core.ProductEvent
import io.github.mrgsrylm.composeshop.ui.components.appbar.AppBar
import io.github.mrgsrylm.composeshop.ui.components.feedback.FeedbackLabel
import io.github.mrgsrylm.composeshop.ui.components.screen.Loading

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Shop(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val tabItems = listOf("Women", "Men", "Kids")
    val pagerState = rememberPagerState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val categoriesState = viewModel.shopCategoriesState
    val categoryState = viewModel.categoryState
    val loggedUser = viewModel.loggedUser

    var chosenProduct: Product? = null
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val result = activityResult.data?.getBooleanExtra(Constants.ADD_TO_CART, false)
            if (result == true) {
                chosenProduct?.let { product ->
                    viewModel.addProduct(product)
                }
            }
            viewModel.onProductEvent(ProductEvent.GetFavorites)
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = "Categories",
                elevation = 5.dp,
                actions = arrayOf(Icons.Outlined.Search)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (categoryState.isCategoryVisible) {
                    if (categoryState.isLoading) {
                        Loading(modifier = Modifier.fillMaxSize())
                    } else {
                        categoryState.category?.let { category ->
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        top = 10.dp,
                                        start = 10.dp,
                                        end = 10.dp,
                                        bottom = screenHeight * 0.15f
                                    ),
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                items(category.products) { product ->
                                    CategoryProduct(
                                        viewModel = viewModel,
                                        product = product,
                                        onProductClick = { chosenProd ->
                                            chosenProduct = chosenProd
                                            val intent =
                                                Intent(
                                                    context,
                                                    AddToCartActivity::class.java
                                                ).apply {
                                                    putExtra(Constants.PRODUCT, chosenProduct)
                                                }
                                            launcher.launch(intent)
                                        },
                                        onFavoriteClick = { chosenProd ->
                                            viewModel.onProductEvent(
                                                ProductEvent.SaveToFavorites(
                                                    chosenProd
                                                )
                                            )
                                        },
                                        onDeleteFavoriteClick = { chosenProd ->
                                            viewModel.onProductEvent(
                                                ProductEvent.DeleteFromFavorites(
                                                    chosenProd
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                } else {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.pagerTabIndicatorOffset(
                                    pagerState,
                                    tabPositions
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    ) {
                        tabItems.forEachIndexed { index, category ->
                            Tab(
                                modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary),
                                text = {
                                    Text(
                                        category,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSecondary
                                    )
                                },
                                selected = pagerState.currentPage == index,
                                onClick = { },
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(10.dp),
                            shape = RoundedCornerShape(20.dp),
                            elevation = 5.dp
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)
                            ) {
                                Text(
                                    text = "SUMMER SALES",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Text(
                                    text = "Up to 50% off",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        if (categoriesState.isLoading) {
                            Loading(modifier = Modifier.fillMaxSize())
                        } else {
                            categoriesState.error?.let { errorMessage ->
                                if (errorMessage.isNotBlank()) {
                                    FeedbackLabel(
                                        modifier = Modifier.fillMaxWidth(),
                                        FeedbackType.Error(errorMessage)
                                    )
                                }
                            }
                            categoriesState.shopCategories?.let { shopCategories ->
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            top = 10.dp,
                                            start = 10.dp,
                                            end = 10.dp,
                                            bottom = screenHeight * 0.15f
                                        ),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    items(shopCategories) { shopCategory ->
                                        ProductCategory(shopCategory = shopCategory) { category ->
                                            viewModel.onCategoriesEvent(
                                                CategoryEvent.GetCategory(
                                                    loggedUser?.token!!,
                                                    category.categoryId,
                                                    category.name
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}