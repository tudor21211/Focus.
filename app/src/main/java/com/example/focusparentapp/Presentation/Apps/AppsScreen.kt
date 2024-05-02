package com.example.focusparentapp.Presentation.Apps

import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.ViewModels.AppInfo
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.Utils.Utils
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AppsScreen(navController: NavController, userId : String, usersViewModel: UsersViewModel) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF172238))
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val colorStops = arrayOf(
        0.2f to Color(0xFF172238),
        0.5f to Color(0xFF121B2E),
        1f to Color(0xFF0A101E)
    )

    var appsInfo by remember {
        mutableStateOf<List<AppInfo>>(emptyList())
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            appsInfo = usersViewModel.getAppsInfoFromUser(userId)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            //.border(1.dp, Color.Black)
            .background(brush = Brush.linearGradient(colorStops = colorStops)),
        horizontalAlignment = Alignment.Start
    ) {
        TopBar(navController = navController, "userMenu/${userId}", "")
        Text(
            text = "Installed Apps",
            modifier = Modifier.padding(start = 15.dp, top = 15.dp),
            fontSize = 25.sp,
            fontFamily = FontFamily(Font(R.font.opensans_res)),
            color = Color.White
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
            content = {
                items(appsInfo.size) {index->
                    appDisplayCard(appName = appsInfo[index].appName, appPackage = appsInfo[index].packageName , icon = Utils().byteStringToDrawable(appsInfo[index].icon)) //TODO REDO THE CONVERSION FROM STRING TO DRAWABLE NOT IN THE UI!!!!
                }
            }
        )
    }
}



@Composable
fun TopBar(navController: NavController, route : String, lastUpdateDate: String, onClick : () -> Unit = {}){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            navController.navigate(route)
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
        if(lastUpdateDate!="")
            Text(text = "last updated: $lastUpdateDate", fontSize = 16.sp, color = Color.Gray)

        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
    }
}


@Composable
fun appDisplayCard(appName: String, appPackage: String, icon: Drawable){

    Card(
        modifier = Modifier
            .padding(3.dp),
        colors =
        CardDefaults.outlinedCardColors(
            containerColor = Color(0xFF232B3F)
        ),
        shape = CardDefaults.elevatedShape,
        border = BorderStroke(1.dp, Color(0xFF101312))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp)
        ){
            Image(
                painter = rememberImagePainter(data = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 15.dp, top = 8.dp, bottom = 8.dp)
            )
            Column {
                Text(
                    text = appName,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.opensans_res)),

                )
                Text(
                    text = appPackage,
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.opensans_res)),
                )
            }

        }

    }

}