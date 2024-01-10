package com.example.focus.Presentation.Screens.Quiz

import android.view.MotionEvent
import android.widget.Spinner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.Presentation.Screens.Landing.Screen
import com.example.focus.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun quizScreen(navController : NavController){
    val systemUiController = rememberSystemUiController()
    val colorStops = arrayOf(
        0.2f to Color(0xFF6353F3),
        0.5f to Color(0xFF3C2EBD),
        1f to Color(0xFF190F6F)
    )

    val colorStopsProgress = arrayOf(
        0.1f to Color(0xFF08FF00),
        0.4f to Color(0xFF77E000),
        1f to Color(0xFFC70000)
    )

    val colorStopsButton = arrayOf(
        0.2f to Color(0xFFB8962F),
        0.5f to Color(0xFF7D853D),
        1f to Color(0xFF100355)
    )

    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF6353F3))
        systemUiController.setNavigationBarColor(Color.Black)
    }




    var sliderPosition by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colorStops = colorStops))
    ) {

        val stepSize = 20f / 40f
        val hourInteger =
            String.format("%.2f", (sliderPosition / stepSize).roundToInt() * stepSize).toFloat()

        Text(
            text = buildAnnotatedString {
                append("How much time do you think you spend on your phone ")
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = Color.Yellow,
                            fontFamily = FontFamily(Font(R.font.opensans_res)),
                        )
                    ) {
                        append("every day?")
                    }
                append("\n\n Take a wild guess!")
                                        },
            color = Color.White,
            fontSize = 21.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp, start = 20.dp, end = 20.dp)
                .fillMaxHeight(.15f),
            fontFamily = FontFamily(Font(R.font.opensans_res)),
            textAlign = TextAlign.Center
        )


        CircularSlider(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .fillMaxHeight(0.6f)
                .align(Alignment.CenterHorizontally),

            onChange = {
                sliderPosition = it * 12f
            },
            progressColor = Color(0xFFEBFC06),
            thumbColor = Color(0xFFEBFC06),
            colorStops = colorStopsProgress
        )

        Text(
            text = "$hourInteger hours",
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight(.4f),
            fontFamily = FontFamily(Font(R.font.opensans_res))
        )

        Button(
            onClick = { navController.navigate("quizResponse/$hourInteger") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A0A05)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 60.dp)
                .fillMaxWidth(.9f)
                .fillMaxHeight(.5f)
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Text(
                text = "Continue",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.opensans_res)
                )
            )
        }
    }

}


    @OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)
    @Composable
    fun CircularSlider(
        modifier: Modifier = Modifier,
        padding: Float = 50f,
        stroke: Float = 20f,
        cap: StrokeCap = StrokeCap.Round,
        touchStroke: Float = 50f,
        thumbColor: Color = Color.Blue,
        progressColor: Color = Color.Black,
        backgroundColor: Color = Color.LightGray,
        debug: Boolean = false,
        onChange: ((Float) -> Unit)? = null,
        colorStops : Array<out Pair<Float, Color>>
    ) {
        var width by remember { mutableStateOf(0) }
        var height by remember { mutableStateOf(0) }
        var angle by remember { mutableStateOf(-60f) }
        var last by remember { mutableStateOf(0f) }
        var down by remember { mutableStateOf(false) }
        var radius by remember { mutableStateOf(0f) }
        var center by remember { mutableStateOf(Offset.Zero) }
        var appliedAngle by remember { mutableStateOf(0f) }

        LaunchedEffect(key1 = angle) {
            var a = angle
            a += 60
            if (a <= 0f) {
                a += 360
            }
            a = a.coerceIn(0f, 300f)
            if (last < 150f && a == 300f) {
                a = 0f
            }
            last = a
            appliedAngle = a
        }
        LaunchedEffect(key1 = appliedAngle) {
            onChange?.invoke(appliedAngle / 300f)
        }
        Canvas(
            modifier = modifier
                .onGloballyPositioned {
                    width = it.size.width
                    height = it.size.height
                    center = Offset(width / 2f, height / 2f)
                    radius = min(width.toFloat(), height.toFloat()) / 2f - padding - stroke / 2f
                }
                .pointerInteropFilter {
                    val x = it.x
                    val y = it.y
                    val offset = Offset(x, y)
                    when (it.action) {

                        MotionEvent.ACTION_DOWN -> {
                            val d = distance(offset, center)
                            val a = angle(center, offset)
                            if (d >= radius - touchStroke / 2f && d <= radius + touchStroke / 2f && a !in -120f..-60f) {
                                down = true
                                angle = a
                            } else {
                                down = false
                            }
                        }

                        MotionEvent.ACTION_MOVE -> {
                            if (down) {
                                angle = angle(center, offset)
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                            down = false
                        }

                        else -> return@pointerInteropFilter false
                    }
                    return@pointerInteropFilter true
                }
        ) {
            drawArc(
                color = backgroundColor,
                startAngle = -240f,
                sweepAngle = 300f,
                topLeft = center - Offset(radius, radius),
                size = Size(radius * 2, radius * 2),
                useCenter = false,
                style = Stroke(
                    width = stroke,
                    cap = cap
                )
            )
            drawArc(
                brush = Brush.linearGradient(colorStops = colorStops),
                startAngle = 120f,
                sweepAngle = appliedAngle,
                topLeft = center - Offset(radius, radius),
                size = Size(radius * 2, radius * 2),
                useCenter = false,
                style = Stroke(
                    width = stroke,
                    cap = cap
                )
            )
            drawCircle(
                color = thumbColor,
                radius = stroke,
                center = center + Offset(
                    radius * cos((120 + appliedAngle) * PI / 180f).toFloat(),
                    radius * sin((120 + appliedAngle) * PI / 180f).toFloat()
                )
            )
            if (debug) {
                drawRect(
                    color = Color.Green,
                    topLeft = Offset.Zero,
                    size = Size(width.toFloat(), height.toFloat()),
                    style = Stroke(
                        4f
                    )
                )
                drawRect(
                    color = Color.Red,
                    topLeft = Offset(padding, padding),
                    size = Size(width.toFloat() - padding * 2, height.toFloat() - padding * 2),
                    style = Stroke(
                        4f
                    )
                )
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(padding, padding),
                    size = Size(width.toFloat() - padding * 2, height.toFloat() - padding * 2),
                    style = Stroke(
                        4f
                    )
                )
                drawCircle(
                    color = Color.Red,
                    center = center,
                    radius = radius + stroke / 2f,
                    style = Stroke(2f)
                )
                drawCircle(
                    color = Color.Red,
                    center = center,
                    radius = radius - stroke / 2f,
                    style = Stroke(2f)
                )
            }
        }
    }


fun angle(center: Offset, offset: Offset): Float {
    val rad = atan2(center.y - offset.y, center.x - offset.x)
    val deg = Math.toDegrees(rad.toDouble())
    return deg.toFloat()
}
fun distance(first: Offset, second: Offset) : Float{
    return sqrt((first.x-second.x).square()+(first.y-second.y).square())
}
fun Float.square(): Float{
    return this*this
}