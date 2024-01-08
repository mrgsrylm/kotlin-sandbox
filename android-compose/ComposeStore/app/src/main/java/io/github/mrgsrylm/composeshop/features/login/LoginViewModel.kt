package io.github.mrgsrylm.composeshop.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.domain.model.user.User
import io.github.mrgsrylm.composeshop.domain.use_cases.local.GetSavedUserUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.local.SaveUserUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.remote.login.LoginUseCase
import io.github.mrgsrylm.composeshop.features.login.core.LoginEvent
import io.github.mrgsrylm.composeshop.features.login.core.LoginState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val getSavedUserUseCase: GetSavedUserUseCase
) : ViewModel() {
    var loginState by mutableStateOf(LoginState())
        private set

    init {
        getSavedUser()
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> login(event.username, event.password)
            is LoginEvent.SaveUser -> saveUser(event.user)
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username, password).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        loginState = loginState.copy(
                            isLoading = true,
                            loggedUser = null,
                            savedUser = null,
                            error = null
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { user ->
                            loginState = loginState.copy(
                                isLoading = false,
                                loggedUser = user,
                                savedUser = null,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        result.message?.let { errorMessage ->
                            loginState = loginState.copy(
                                isLoading = false,
                                error = errorMessage,
                                loggedUser = null,
                                savedUser = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun saveUser(user: User) {
        viewModelScope.launch {
            saveUserUseCase(user).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        loginState = loginState.copy(
                            isLoading = true,
                            loggedUser = null,
                            savedUser = null,
                            error = null
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { user ->
                            loginState = loginState.copy(
                                isLoading = false,
                                savedUser = user,
                                loggedUser = null,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        result.message?.let { errorMessage ->
                            loginState = loginState.copy(
                                isLoading = false,
                                error = errorMessage,
                                savedUser = null,
                                loggedUser = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getSavedUser() {
        viewModelScope.launch {
            getSavedUserUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        loginState = loginState.copy(
                            isLoading = true,
                            loggedUser = null,
                            savedUser = null,
                            error = null
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { user ->
                            loginState = loginState.copy(
                                isLoading = false,
                                savedUser = user,
                                loggedUser = null,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        result.message?.let { errorMessage ->
                            loginState = loginState.copy(
                                isLoading = false,
                                error = errorMessage,
                                savedUser = null,
                                loggedUser = null
                            )
                        }
                    }
                }
            }
        }
    }
}