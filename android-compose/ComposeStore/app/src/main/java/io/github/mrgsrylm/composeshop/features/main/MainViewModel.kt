package io.github.mrgsrylm.composeshop.features.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.domain.model.user.User
import io.github.mrgsrylm.composeshop.domain.use_cases.local.DeleteFromFavoritesUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.local.GetFavoritesUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.local.GetSavedUserUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.local.SaveToFavoritesUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.remote.category.GetCategoriesUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.remote.category.GetCategoryUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.remote.product.GetProductsUseCase
import io.github.mrgsrylm.composeshop.features.main.favorites.core.FavoritesState
import io.github.mrgsrylm.composeshop.features.main.shop.core.CategoryEvent
import io.github.mrgsrylm.composeshop.features.main.shop.core.CategoryState
import io.github.mrgsrylm.composeshop.features.main.shop.core.ShopCategoriesState
import io.github.mrgsrylm.composeshop.features.product.core.NewProductsState
import io.github.mrgsrylm.composeshop.features.product.core.ProductEvent
import io.github.mrgsrylm.composeshop.features.product.core.SaleProductsState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSavedUserUseCase: GetSavedUserUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val saveToFavoritesUseCase: SaveToFavoritesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    var loggedUser: User? = null
        private set
    var screenLoadingState by mutableStateOf(true)
        private set
    var shopCategoriesState by mutableStateOf(ShopCategoriesState())
        private set
    var categoryState by mutableStateOf(CategoryState())
        private set
    var favoritesState by mutableStateOf(FavoritesState())
        private set
    var newProductsState by mutableStateOf(NewProductsState())
        private set
    var saleProductsState by mutableStateOf(SaleProductsState())
        private set

    init {
        getSavedUser()
    }

    private val _cartProducts = mutableListOf<Product>()
    val cartProducts: List<Product> = _cartProducts

    fun addProduct(product: Product) {
        _cartProducts.add(product)
    }

    fun onCategoriesEvent(categoryEvent: CategoryEvent) {
        when (categoryEvent) {
            is CategoryEvent.GetCategory -> getCategory(
                categoryEvent.token,
                categoryEvent.categoryId,
                categoryEvent.name
            )

            is CategoryEvent.CloseCategory -> closeCategory()
        }
    }

    private fun getCategories(token: String) {
        viewModelScope.launch {
            getCategoriesUseCase(token).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        shopCategoriesState = shopCategoriesState.copy(
                            isLoading = true,
                            shopCategories = null,
                            error = null
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { categories ->
                            shopCategoriesState = shopCategoriesState.copy(
                                isLoading = false,
                                shopCategories = categories,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        result.message?.let { errorMsg ->
                            shopCategoriesState = shopCategoriesState.copy(
                                isLoading = false,
                                shopCategories = null,
                                error = errorMsg
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getCategory(token: String, categoryId: String, name: String) {
        viewModelScope.launch {
            getCategoryUseCase(token, categoryId, name).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        categoryState = categoryState.copy(
                            isCategoryVisible = true,
                            isLoading = true,
                            category = null,
                            error = null
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { category ->
                            categoryState = categoryState.copy(
                                isCategoryVisible = true,
                                isLoading = false,
                                category = category,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        result.message?.let { errorMsg ->
                            categoryState = categoryState.copy(
                                isCategoryVisible = true,
                                isLoading = false,
                                category = null,
                                error = errorMsg
                            )
                        }
                    }
                }
            }
        }
    }

    private fun closeCategory() {
        categoryState = categoryState.copy(
            isLoading = false,
            category = null,
            error = null,
            isCategoryVisible = false
        )
    }

    private fun getSavedUser() {
        viewModelScope.launch {
            getSavedUserUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        screenLoadingState = true
                    }

                    is Resource.Success -> {
                        screenLoadingState = false
                        result.data?.let { user ->
                            loggedUser = user
                            getCategories(loggedUser?.token!!)
                            onProductEvent(ProductEvent.GetFavorites)
                            getNewProducts(loggedUser?.token!!, 0)
                            getSaleProducts(loggedUser?.token!!, 10)
                        }
                    }

                    is Resource.Error -> {
                        screenLoadingState = false
                    }
                }
            }
        }
    }

    fun onProductEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.SaveToFavorites -> saveToFavorites(event.product)
            is ProductEvent.GetFavorites -> getFavorites()
            is ProductEvent.DeleteFromFavorites -> deleteFromFavorites(event.product)
        }
    }

    private fun saveToFavorites(product: Product) {
        viewModelScope.launch {
            saveToFavoritesUseCase(product).collect { result ->
                screenLoadingState = when (result) {
                    is Resource.Loading -> true
                    is Resource.Success -> {
                        onProductEvent(ProductEvent.GetFavorites)
                        false
                    }

                    is Resource.Error -> false
                }
            }
        }
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        favoritesState = favoritesState.copy(
                            isLoading = true,
                            favorites = null,
                            error = null
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { favorites ->
                            favoritesState = favoritesState.copy(
                                isLoading = false,
                                favorites = favorites,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        result.message?.let { errorMsg ->
                            favoritesState = favoritesState.copy(
                                isLoading = false,
                                favorites = null,
                                error = errorMsg
                            )
                        }
                    }
                }
            }
        }
    }

    private fun deleteFromFavorites(product: Product) {
        viewModelScope.launch {
            deleteFromFavoritesUseCase(product).collect { result ->
                screenLoadingState = when (result) {
                    is Resource.Loading -> true
                    is Resource.Success -> {
                        onProductEvent(ProductEvent.GetFavorites)
                        false
                    }

                    is Resource.Error -> false
                }
            }
        }
    }

    private fun getNewProducts(token: String, skip: Int) {
        viewModelScope.launch {
            getProductsUseCase(token, skip).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        newProductsState = newProductsState.copy(
                            loading = true,
                            newProducts = null,
                            errorMsg = null
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { newProducts ->
                            newProductsState = newProductsState.copy(
                                loading = false,
                                newProducts = newProducts,
                                errorMsg = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        result.message?.let { errorMessage ->
                            newProductsState = newProductsState.copy(
                                loading = false,
                                newProducts = null,
                                errorMsg = errorMessage
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getSaleProducts(token: String, skip: Int) {
        viewModelScope.launch {
            getProductsUseCase(token, skip).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        saleProductsState = saleProductsState.copy(
                            loading = true,
                            saleProducts = null,
                            errorMsg = null
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { saleProducts ->
                            saleProductsState = saleProductsState.copy(
                                loading = false,
                                saleProducts = saleProducts,
                                errorMsg = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        result.message?.let { errorMessage ->
                            saleProductsState = saleProductsState.copy(
                                loading = false,
                                saleProducts = null,
                                errorMsg = errorMessage
                            )
                        }
                    }
                }
            }
        }
    }
}