

package com.example.makeitso.screens.settings

import com.example.makeitso.LOGIN_SCREEN
import com.example.makeitso.SIGN_UP_SCREEN
import com.example.makeitso.SPLASH_SCREEN
import com.example.makeitso.model.service.AccountService
import com.example.makeitso.model.service.LogService
import com.example.makeitso.model.service.StorageService
import com.example.makeitso.screens.MakeItSoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  logService: LogService,
  private val accountService: AccountService,
  private val storageService: StorageService
) : MakeItSoViewModel(logService) {
  val uiState = accountService.currentUser.map { SettingsUiState(it.isAnonymous) }

  fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOGIN_SCREEN)

  fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

  fun onSignOutClick(restartApp: (String) -> Unit) {
    launchCatching {
      accountService.signOut()
      restartApp(SPLASH_SCREEN)
    }
  }

  fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
    launchCatching {
      accountService.deleteAccount()
      restartApp(SPLASH_SCREEN)
    }
  }
}
