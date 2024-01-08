package io.github.mrgsrylm.composeshop.features.main.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.mrgsrylm.composeshop.features.main.MainViewModel
import io.github.mrgsrylm.composeshop.features.main.profile.components.ProfileListItem
import io.github.mrgsrylm.composeshop.features.main.profile.utils.profileItems
import io.github.mrgsrylm.composeshop.ui.components.appbar.AppBar
import io.github.mrgsrylm.composeshop.ui.components.screen.BottomPaddingColumn
import io.github.mrgsrylm.composeshop.ui.components.screen.ScreenTitle
import io.github.mrgsrylm.composeshop.ui.theme.Gray

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Profile(viewModel: MainViewModel) {
    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            AppBar(
                isBackEnabled = false,
                actions = arrayOf(Icons.Outlined.Search)
            )
        },
        content = {
            BottomPaddingColumn {
                ScreenTitle(title = "My Profile")
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(40.dp))
                            .width(60.dp)
                            .height(60.dp),
                        model = viewModel.loggedUser?.image,
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "${viewModel.loggedUser?.firstName} ${viewModel.loggedUser?.lastName}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${viewModel.loggedUser?.email}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Gray,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(profileItems) { profileListItem ->
                        ProfileListItem(item = profileListItem)
                    }
                }
            }
        })
}