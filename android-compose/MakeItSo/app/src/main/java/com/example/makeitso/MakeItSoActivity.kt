

package com.example.makeitso

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint

// MakeItSoActivity starts the first composable, which uses material cards that are still experimental.
// TODO: Update material dependency and experimental annotations once the API stabilizes.
@AndroidEntryPoint
@ExperimentalMaterialApi
class MakeItSoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent { MakeItSoApp() }
  }
}
