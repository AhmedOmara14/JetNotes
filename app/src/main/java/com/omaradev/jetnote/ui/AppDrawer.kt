package com.omaradev.jetnote.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.jetnote.R
import com.omaradev.jetnote.routing.Screen

@Composable
fun AppDrawer(currentScreen: Screen, onScreenSelected: (Screen) -> Unit) {
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxSize()) {
        TopBarDrawer()

        Divider(color = Color.Black.copy(alpha = .2f))

        Spacer(modifier = Modifier.padding(8.dp))

        ScreenNavigationButton(
            imageId = Icons.Filled.Home, label = "Home",
            isSelected = currentScreen == Screen.Notes
        ) { onScreenSelected.invoke(Screen.Notes) }

        ScreenNavigationButton(
            imageId = Icons.Filled.Delete,
            label = "Trash",
            isSelected = currentScreen == Screen.Trash
        ) { onScreenSelected.invoke(Screen.Trash) }

    }
}

@Composable
fun TopBarDrawer() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Image(
            painterResource(id = R.drawable.ic_menu),
            contentDescription = "Icon Drawer",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = TextStyle(color = Color.Black, fontSize = 16.sp),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun ScreenNavigationButton(
    imageId: ImageVector, label: String, isSelected: Boolean, onClickNavigationButton: () -> Unit
) {

    val textColor = if (isSelected) colorResource(id = R.color.colorPrimary)
    else colorResource(id = R.color.gray)

    val imageAlpha = if (isSelected) 1f else 0.4f

    val backgroundColor =
        if (isSelected) colorResource(id = R.color.colorPrimary).copy(alpha = .12f)
        else colorResource(id = R.color.white)

    Surface(
        color = backgroundColor,
        modifier = Modifier
            .padding(start = 8.dp, top = 4.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp)
                .clickable(onClick = onClickNavigationButton)
        ) {
            Image(
                imageVector = imageId,
                contentDescription = label,
                alpha = imageAlpha,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Text(
                text = label,
                style = TextStyle(color = textColor, fontSize = 16.sp),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
