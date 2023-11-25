package com.example.focus.Presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focus.R


@Composable
fun landingPage () {
    Box(modifier = Modifier
        .fillMaxSize(1f)
        .background(Color(0xFF0657EC))) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.focuslogo),
                contentDescription = "focusLogo",
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .fillMaxHeight(.2f)
                    .offset(x = -20.dp, y = -10.dp)
            )
            Text(
                text =
                "Welcome to focus. – Your gateway to distraction-free living. Seize control of your time, block out the noise, and reclaim your focus. Embrace the power to curate your digital space and amplify your productivity. Let's turn intention into achievement. #focusOnWhatMatters",
                modifier = Modifier

                    .padding(horizontal = 10.dp),
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(60.dp))

            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(shape = CutCornerShape(30))) {
                Image(
                    painter = painterResource(id = R.drawable.landpageinteract),
                    contentDescription = "landpageinteract",
                    modifier = Modifier
                        .fillMaxWidth(.90f)
                        .size(350.dp)

                )
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F93F3)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
                    .size(width = 300.dp, height = 70.dp)
                    .clip(shape = RoundedCornerShape(10.dp))) {
                Text(text = "Get Started", color = Color.White, fontSize = 20.sp)
            }
        }




    }
}
