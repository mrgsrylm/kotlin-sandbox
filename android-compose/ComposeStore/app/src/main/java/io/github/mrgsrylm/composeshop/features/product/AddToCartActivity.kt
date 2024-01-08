package io.github.mrgsrylm.composeshop.features.product

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import dagger.hilt.android.AndroidEntryPoint
import io.github.mrgsrylm.composeshop.common.FeedbackType
import io.github.mrgsrylm.composeshop.common.SpinnerType
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.features.product.common.Constants
import io.github.mrgsrylm.composeshop.features.product.core.ProductEvent
import io.github.mrgsrylm.composeshop.ui.components.appbar.AppBar
import io.github.mrgsrylm.composeshop.ui.components.button.CustomButton
import io.github.mrgsrylm.composeshop.ui.components.feedback.FeedbackLabel
import io.github.mrgsrylm.composeshop.ui.components.product.CircularButton
import io.github.mrgsrylm.composeshop.ui.components.product.ProductPrice
import io.github.mrgsrylm.composeshop.ui.components.product.ProductRating
import io.github.mrgsrylm.composeshop.ui.components.product.ProductSubtitle
import io.github.mrgsrylm.composeshop.ui.components.product.ProductTitle
import io.github.mrgsrylm.composeshop.ui.components.screen.Loading
import io.github.mrgsrylm.composeshop.ui.components.spinner.CustomSpinner
import io.github.mrgsrylm.composeshop.ui.theme.ComposeShopTheme

@AndroidEntryPoint
class AddToCartActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShopTheme {
                val viewModel: AddToCartViewModel = hiltViewModel()
                val favoritesState = viewModel.favoritesState
                val screenLoading = viewModel.screenLoadingState
                val context = LocalContext.current
                val product = intent.extras?.getSerializable(Constants.PRODUCT) as Product

                val found = viewModel.favoritesState.favorites?.find { indexedProduct ->
                    indexedProduct.productId == product.productId && indexedProduct.category == product.category
                }

                BackHandler {
                    sendResult(context, false)
                    finish()
                }
                Scaffold(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                        .fillMaxSize(),
                    topBar = {
                        AppBar(
                            title = product.title,
                            actions = arrayOf(Icons.Outlined.Share)
                        )
                    },
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            if (screenLoading) {
                                Loading(modifier = Modifier.fillMaxSize())
                            } else {
                                if (favoritesState.error != null && favoritesState.error.isNotBlank()) {
                                    FeedbackLabel(
                                        modifier = Modifier.fillMaxSize(),
                                        feedbackType = FeedbackType.Error(favoritesState.error)
                                    )
                                } else {
                                    HorizontalPager(
                                        count = product.images.size, modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.6f),
                                        contentPadding = PaddingValues(end = 20.dp)
                                    ) { pageIndex ->
                                        AsyncImage(
                                            model = product.images[pageIndex],
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(10.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceAround,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            CustomSpinner(SpinnerType.Size)
                                            CustomSpinner(SpinnerType.Color)
                                            CircularButton(
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .width(40.dp),
                                                iconModifier = Modifier
                                                    .width(15.dp)
                                                    .height(15.dp),
                                                icon = if (found != null) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                                tint = if (found != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary,
                                                onClick = {
                                                    if (found != null) {
                                                        viewModel.onProductEvent(
                                                            ProductEvent.DeleteFromFavorites(
                                                                product
                                                            )
                                                        )
                                                    } else {
                                                        viewModel.onProductEvent(
                                                            ProductEvent.SaveToFavorites(
                                                                product
                                                            )
                                                        )
                                                    }
                                                },
                                                iconDrawable = null
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(20.dp))
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            ProductTitle(product = product)
                                            ProductPrice(
                                                product = product,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                        ProductSubtitle(product = product)
                                        ProductRating(product = product)
                                        Text(text = product.description)
                                        Spacer(modifier = Modifier.height(20.dp))
                                        CustomButton(modifier = Modifier
                                            .fillMaxWidth(),
                                            textModifier = Modifier.padding(vertical = 10.dp),
                                            text = "ADD TO CART",
                                            onClick = {
                                                sendResult(context, true)
                                            })
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    private fun sendResult(context: Context, result: Boolean) {
        val data = Intent().apply {
            putExtra(Constants.ADD_TO_CART, result)
        }
        setResult(Activity.RESULT_OK, data)
        (context as ComponentActivity).finish()
    }
}