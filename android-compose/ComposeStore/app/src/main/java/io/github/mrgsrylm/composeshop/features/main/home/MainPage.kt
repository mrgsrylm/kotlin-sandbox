package io.github.mrgsrylm.composeshop.features.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mrgsrylm.composeshop.R
import io.github.mrgsrylm.composeshop.features.main.MainViewModel
import io.github.mrgsrylm.composeshop.features.main.home.components.main_area.MainArea
import io.github.mrgsrylm.composeshop.features.main.home.utils.ProductType
import io.github.mrgsrylm.composeshop.ui.components.button.CustomButton
import io.github.mrgsrylm.composeshop.ui.components.screen.BottomPaddingColumn

@Composable
fun MainPage(
    viewModel: MainViewModel
) {
    BottomPaddingColumn(verticalScroll = true, justBottomPadding = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.main_page_top_image),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(bottom = 10.dp, start = 10.dp)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Fashion\nSale",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.metrophobic_regular, FontWeight.Bold))
                )
                CustomButton(modifier = Modifier
                    .width(200.dp),
                    textModifier = Modifier.padding(vertical = 5.dp),
                    text = "Check",
                    onClick = { })
            }
        }
        MainArea(
            productType = ProductType.New,
            mainAreaLabel = "New",
            mainAreaSubtitle = "You've never seen it before!",
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(10.dp))
        MainArea(
            productType = ProductType.Sale,
            mainAreaLabel = "Sale",
            mainAreaSubtitle = "Super summer sale",
            viewModel = viewModel
        )
    }
}