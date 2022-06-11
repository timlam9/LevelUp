package presentation.ui.composables

import Green
import Red
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.VectorConverter
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProgressChart(
    modifier: Modifier = Modifier,
    total: Int,
    completed: Int,
    color1: Color = Green,
    color2: Color = Red,
    size: Dp = 250.dp,
    stroke: Dp = 10.dp,
) {
    val angle: Animatable<Float, AnimationVector1D> = remember { Animatable(0f) }
    LaunchedEffect(angle) {
        launch {
            angle.animateTo(
                targetValue = 360f,
                animationSpec = tween(durationMillis = 1000)
            )
        }
    }

    val toCircleRatio = ((360 * completed) / total).toFloat()
    val anglesPadding = when {
        size <= 180.dp -> 8f
        size <= 250.dp -> 6f
        size <= 300.dp -> 4f
        else -> 3f
    }

    val startAngle1 = anglesPadding / 2f
    val sweepAngle1 = toCircleRatio - anglesPadding
    val startAngle2 = toCircleRatio + anglesPadding + anglesPadding / 2f
    val sweepAngle2 = 360f - (toCircleRatio + anglesPadding * 3)

    Canvas(
        modifier = modifier.size(size),
        onDraw = {
            rotate(angle.value) {
                drawArc(
                    color = color1,
                    startAngle = startAngle1,
                    sweepAngle = sweepAngle1,
                    useCenter = false,
                    style = Stroke(stroke.toPx(), cap = StrokeCap.Round),
                    size = Size(size.toPx(), size.toPx())
                )
                drawArc(
                    color = if (total != completed) color2 else color1,
                    startAngle = startAngle2,
                    sweepAngle = sweepAngle2,
                    useCenter = false,
                    style = Stroke(stroke.toPx(), cap = StrokeCap.Round),
                    size = Size(size.toPx(), size.toPx())
                )
            }
        }
    )
}

@Composable
fun CustomCanvasAnimation(
    colorToAnimate: Color = MaterialTheme.colors.background,
    content: @Composable () -> Unit
) {
    val showContent = remember { mutableStateOf(false) }
    val bgColor = MaterialTheme.colors.surface
    val angle: Animatable<Float, AnimationVector1D> = remember { Animatable(0f) }
    val size = remember { Animatable(0.2f) }
    val color = remember {
        Animatable(
            initialValue = bgColor,
            typeConverter = Color.VectorConverter(ColorSpaces.LinearSrgb)
        )
    }

    LaunchedEffect(angle, color, size) {
        launch {
            delay(1000)
            showContent.value = true
        }
        launch {
            size.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing,
                    delayMillis = 250
                )
            )
        }
        launch {
            angle.animateTo(
                targetValue = 360f,
                animationSpec = tween(
                    durationMillis = 500
                )
            )
        }
        launch {
            color.animateTo(
                targetValue = colorToAnimate,
                animationSpec = tween(
                    durationMillis = 1500,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scale(size.value)
            .drawBehind {
                rotate(angle.value) {
                    drawRoundRect(
                        color = color.value,
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                }
            },
        content = {
            AnimatedVisibility(showContent.value) {
                content()
            }
        }
    )
}