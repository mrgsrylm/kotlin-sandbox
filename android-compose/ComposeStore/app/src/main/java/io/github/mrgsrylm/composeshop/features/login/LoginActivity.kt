@file:SuppressLint("UnusedMaterialScaffoldPaddingParameter")
package io.github.mrgsrylm.composeshop.features.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.mrgsrylm.composeshop.ui.components.appbar.AppBar
import io.github.mrgsrylm.composeshop.ui.theme.ComposeShopTheme

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShopTheme {
                val viewModel: LoginViewModel = hiltViewModel()
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    topBar = {
                        AppBar()
                    },
                    content = {
                        Login(viewModel = viewModel)
                    }
                )
            }
        }
    }
}