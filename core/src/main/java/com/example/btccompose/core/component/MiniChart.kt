package com.example.btccompose.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun MiniChart(
    modifier: Modifier = Modifier
) {
    val animationProgress = remember { Animatable(0f) }
    val chartColor = Color(0xFF00B07C)

    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = modifier
            .width(60.dp)
            .height(24.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path()
            val fillPath = Path()
            
            val points = listOf(
                0f, 0.2f, 0.1f, 0.3f, -0.2f, -0.4f, -0.1f, 
                0.2f, 0.4f, 0.3f, 0.5f, 0.3f, 0.4f, 0.2f
            )
            val xStep = size.width / (points.size - 1)
            val yScale = size.height / 3f

            // Ana çizgi için pathi
            path.moveTo(0f, size.height / 2)
            points.forEachIndexed { index, y ->
                val x = index * xStep
                val animatedY = size.height / 2 - y * yScale * animationProgress.value
                path.lineTo(x, animatedY)
            }

            // Gradient dolgu için pathi
            fillPath.moveTo(0f, size.height)
            points.forEachIndexed { index, y ->
                val x = index * xStep
                val animatedY = size.height / 2 - y * yScale * animationProgress.value
                fillPath.lineTo(x, animatedY)
            }
            fillPath.lineTo(size.width, size.height)
            fillPath.close()

            // Gradient dolguyu çiziyoruz
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        chartColor.copy(alpha = 0.1f),
                        chartColor.copy(alpha = 0.02f)
                    )
                )
            )

            // Ana çizgiyi çiziyoruz
            drawPath(
                path = path,
                color = chartColor,
                style = Stroke(
                    width = 1.dp.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }
} 