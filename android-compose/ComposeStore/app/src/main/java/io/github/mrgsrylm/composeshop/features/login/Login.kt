package io.github.mrgsrylm.composeshop.features.login

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.common.FeedbackType
import io.github.mrgsrylm.composeshop.features.login.components.ForgotPassword
import io.github.mrgsrylm.composeshop.features.login.components.LoginTextField
import io.github.mrgsrylm.composeshop.features.login.components.SocialCard
import io.github.mrgsrylm.composeshop.features.login.core.LoginEvent
import io.github.mrgsrylm.composeshop.features.login.utils.LoginFieldType
import io.github.mrgsrylm.composeshop.features.login.utils.SocialType
import io.github.mrgsrylm.composeshop.features.main.MainActivity
import io.github.mrgsrylm.composeshop.ui.components.button.CustomButton
import io.github.mrgsrylm.composeshop.ui.components.feedback.FeedbackLabel
import io.github.mrgsrylm.composeshop.ui.components.screen.Loading
import io.github.mrgsrylm.composeshop.ui.components.screen.ScreenTitle

@Composable
fun Login(viewModel: LoginViewModel) {
    val loginState = viewModel.loginState
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ScreenTitle(
            title = "Login", modifier = Modifier
                .padding(start = 20.dp)
        )
        loginState.savedUser?.let {
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as ComponentActivity).finish()
        }
        loginState.loggedUser?.let {
            viewModel.onEvent(LoginEvent.SaveUser(it))
        }
        if (loginState.isLoading) {
            Loading(modifier = Modifier.fillMaxWidth())
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginTextField(type = LoginFieldType.Email, text = emailState) {
                    emailState = it
                }
                Spacer(modifier = Modifier.height(20.dp))
                LoginTextField(type = LoginFieldType.Password, text = passwordState) {
                    passwordState = it
                }
                Spacer(modifier = Modifier.height(20.dp))
                ForgotPassword()
                Spacer(modifier = Modifier.height(20.dp))
                loginState.error?.let { errorMessage ->
                    if (errorMessage.isNotBlank()) {
                        FeedbackLabel(
                            modifier = Modifier.fillMaxWidth(),
                            FeedbackType.Error(errorMessage)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                CustomButton(modifier = Modifier
                    .fillMaxWidth(),
                    textModifier = Modifier.padding(vertical = 10.dp),
                    text = "LOGIN",
                    onClick = {
                        viewModel.onEvent(LoginEvent.Login(emailState, passwordState))
                    })
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Or login with social account",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                SocialCard(SocialType.Google)
                Spacer(modifier = Modifier.width(30.dp))
                SocialCard(SocialType.Facebook)
            }
        }
    }
}