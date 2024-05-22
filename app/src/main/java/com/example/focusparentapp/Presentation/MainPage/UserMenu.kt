package com.example.focusparentapp.Presentation.MainPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focusparentapp.Navigation.Screens
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.WebSockets.WebSocketConnector
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun UserMenu(navController: NavController, userId : String, usersViewModel: UsersViewModel){

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFFE2E1EB))
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var deviceType by remember {
        mutableStateOf<String>("")
    }
    var email by remember {
        mutableStateOf<String>("")
    }

    LaunchedEffect(Unit) {
        deviceType = withContext(Dispatchers.IO) {
            usersViewModel.getDeviceType(userId)
        }
        email = withContext(Dispatchers.IO) {
            usersViewModel.getUserEmail(userId)
        }
    }

    LaunchedEffect(Unit) {

    }

    val colorStops = arrayOf(
        0.2f to Color(0xFFE2E1EB),
        0.5f to Color(0xFFCFCDE4),
        1f to Color(0xFFC5C2DD)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.border(1.dp, Color.Black)
            .background(brush = Brush.linearGradient(colorStops = colorStops)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(navController = navController)
        Column(
            modifier = Modifier
                //.border(1.dp, Color.Black)
                .fillMaxWidth(.95f)
                .fillMaxHeight()
        ) {
            imageWidget(
                painterResource = painterResource(id = R.drawable.girl),
                "Child One",
                deviceType,
                email
            )
            Spacer(modifier = Modifier.fillMaxHeight(.2f))
            Text(
                text = "Features",
                fontSize = 22.sp,
                modifier = Modifier.padding(start =5.dp),
                fontFamily = FontFamily(Font(R.font.opensans_res))
            )
            featureCategory("Internet",
                painterResource(id = R.drawable.internet),
                80.dp,
                5.dp,
                onClick = {
                    navController.navigate("websitesScreen/${userId}")
                })
            featureCategory("Device use",
                painterResource(id = R.drawable.stats),
                70.dp,
                10.dp,
                onClick = {
                    navController.navigate("deviceUse/${userId}")
                })
            featureCategory("Location",
                painterResource(id = R.drawable.location),
                70.dp,
                10.dp,
                onClick = {
                    navController.navigate("locationScreen/${userId}")
                })
            featureCategory("Restrictions",
                painterResource(id = R.drawable.blockapps),
                70.dp,
                10.dp,
                onClick = {
                    navController.navigate("restrictionsScreen/${userId}")
                })

        }
    }

}



@Composable
fun TopBar(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
        //.border(1.dp, Color.Black)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        IconButton(onClick = {
            navController.navigate(Screens.MainPage.route)
        }){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color.Black
            )
        }

        IconButton(onClick = {
        }){
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color.Black
            )
        }
    }

}

@Composable
fun imageWidget(painterResource : Painter, userName : String, deviceName : String, email : String){
    Row(
        modifier = Modifier
            .padding(top = 30.dp, bottom = 5.dp, end = 10.dp)
            //.border(1.dp, Color.Black)
            .fillMaxWidth(),
    ){
        Box(
            modifier = Modifier
                .size(125.dp)
                .clip(CircleShape)

            ,
        ) {
            Image(
                painter = painterResource,
                contentDescription = null,
            )
        }
        Column(
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Text(
                text = userName,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 15.dp),
                fontWeight = FontWeight(300),
                fontFamily = FontFamily(Font(R.font.opensans_res)),
                color = Color.Black
            )
            Text(
                text = deviceName,
                fontFamily = FontFamily(Font(R.font.opensans_res)),
                color = Color.Gray,
                fontSize = 15.sp
            )
            Text(
                text = email,
                fontFamily = FontFamily(Font(R.font.opensans_res)),
                color = Color.Gray,
                fontSize = 15.sp
            )
        }

    }
}


@Composable
fun featureCategory(type : String, cardImage : Painter, imageSize : Dp, fieldPadding : Dp, onClick: () -> Unit){

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .size(120.dp)
            .padding(top = fieldPadding)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.elevatedCardColors(Color(0xFFBAB8D5))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ){
            Spacer(modifier = Modifier.fillMaxWidth(.03f))

            Box(modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.Center) {
                Image(
                    painter = cardImage,
                    contentDescription = "",
                    modifier = Modifier.size(imageSize)
                )
            }
            Text(
                text = type,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.opensans_res)),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }

}