

package com.example.makeitso.screens.login

import androidx.compose.runtime.mutableStateOf
import com.example.makeitso.LOGIN_SCREEN
import com.example.makeitso.SETTINGS_SCREEN
import com.example.makeitso.common.ext.isValidEmail
import com.example.makeitso.common.snackbar.SnackbarManager
import com.example.makeitso.model.service.AccountService
import com.example.makeitso.model.service.LogService
import com.example.makeitso.screens.MakeItSoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.makeitso.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val accountService: AccountService,
  logService: LogService
) : MakeItSoViewModel(logService) {
  var uiState = mutableStateOf(LoginUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }

  fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    if (password.isBlank()) {
      SnackbarManager.showMessage(AppText.empty_password_error)
      return
    }

    launchCatching {
      accountService.authenticate(email, password)
      openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
    }
  }

  fun onForgotPasswordClick() {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    launchCatching {
      accountService.sendRecoveryEmail(email)
      SnackbarManager.showMessage(AppText.recovery_email_sent)
    }
  }
}
