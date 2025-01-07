package com.example.btccompose.presentation.component

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.abs
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.example.btccompose.R
import com.example.btccompose.utils.formatVolume
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@Composable
fun LineChart(
    dataPoints: List<Float>,
    xLabels: List<String>,
    chartColor: Color = MaterialTheme.colorScheme.primary,
    animationDuration: Int = 1000
) {
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    val density = LocalDensity.current
    val yLabels = remember(dataPoints) { calculateYAxisLabels(dataPoints) }
    val animatedDataPoints = remember(dataPoints) {
        Animatable(0f)
    }

    LaunchedEffect(dataPoints) {
        animatedDataPoints.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = FastOutSlowInEasing
            )
        )
    }

    val textPaint = remember {
        android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textSize = density.run { 14.dp.toPx() }
            isAntiAlias = true
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures { offset ->
                                    val points = calculateAnimatedPoints(
                                        IntSize(size.width, size.height),
                                        dataPoints,
                                        animatedDataPoints.value
                                    )
                                    val index = findNearestPoint(points, offset)
                                    selectedIndex.value = index
                                }
                            }
                            .pointerInput(Unit) {
                                detectHorizontalDragGestures { change, _ ->
                                    val points = calculateAnimatedPoints(
                                        IntSize(size.width, size.height),
                                        dataPoints,
                                        animatedDataPoints.value
                                    )
                                    val index = findNearestPoint(points, change.position)
                                    selectedIndex.value = index
                                }
                            }
                    ) {
                        val points = calculateAnimatedPoints(
                            IntSize(size.width.toInt(), size.height.toInt()),
                            dataPoints,
                            animatedDataPoints.value
                        )

                        drawGridLines(
                            size = IntSize(size.width.toInt(), size.height.toInt()),
                            yLabels = yLabels
                        )

                        drawPath(
                            path = createAnimatedLinePath(points, animatedDataPoints.value),
                            color = chartColor,
                            style = Stroke(
                                width = 3.dp.toPx(),
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )

                        drawPath(
                            path = createAnimatedGradientPath(points, size, animatedDataPoints.value),
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    chartColor.copy(alpha = 0.3f * animatedDataPoints.value),
                                    chartColor.copy(alpha = 0.0f)
                                )
                            )
                        )

                        points.forEachIndexed { index, point ->
                            val pointAlpha = animatedDataPoints.value
                            drawCircle(
                                color = if (index == selectedIndex.value)
                                    Color.White.copy(alpha = pointAlpha)
                                else chartColor.copy(alpha = pointAlpha),
                                radius = if (index == selectedIndex.value) 6.dp.toPx() else 4.dp.toPx(),
                                center = point,
                                style = if (index == selectedIndex.value)
                                    Stroke(width = 2.dp.toPx())
                                else Fill
                            )
                        }

                        selectedIndex.value?.let { index ->
                            if (animatedDataPoints.value > 0.5f) {
                                drawTooltip(
                                    point = points[index],
                                    value = dataPoints[index],
                                    label = xLabels[index],
                                    textPaint = textPaint
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    yLabels.asReversed().forEach { value ->
                        Text(
                            text = value.toString().formatVolume(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.6f * animatedDataPoints.value
                            )
                        )
                    }
                }
            }
        }
    }
}

private fun calculateAnimatedPoints(
    size: IntSize,
    dataPoints: List<Float>,
    animationProgress: Float
): List<Offset> {
    if (dataPoints.isEmpty()) return emptyList()

    val maxY = dataPoints.maxOrNull() ?: 0f
    val minY = dataPoints.minOrNull() ?: 0f
    val yRange = (maxY - minY).coerceAtLeast(0.01f)

    val xStep = size.width.toFloat() / (dataPoints.size - 1)

    return dataPoints.mapIndexed { index, value ->
        val animatedValue = minY + (value - minY) * animationProgress
        Offset(
            x = index * xStep,
            y = size.height.toFloat() * (1f - (animatedValue - minY) / yRange)
        )
    }
}

private fun findNearestPoint(points: List<Offset>, touchPoint: Offset): Int {
    return points.withIndex().minByOrNull { (_, point) ->
        abs(point.x - touchPoint.x)
    }?.index ?: 0
}

private fun DrawScope.drawGridLines(size: IntSize, yLabels: List<Float>) {
    val yStep = size.height.toFloat() / (yLabels.size - 1)

    yLabels.forEachIndexed { index, _ ->
        val y = index * yStep
        drawLine(
            color = Color.Gray.copy(alpha = 0.2f),
            start = Offset(0f, y),
            end = Offset(size.width.toFloat(), y),
            strokeWidth = 1.dp.toPx()
        )
    }
}

private fun createAnimatedLinePath(points: List<Offset>, progress: Float): Path {
    return Path().apply {
        if (points.isNotEmpty()) {
            moveTo(points.first().x, points.first().y)
            val animatedPoints = points.take((points.size * progress).toInt() + 1)
            animatedPoints.drop(1).forEach { point ->
                lineTo(point.x, point.y)
            }
        }
    }
}

private fun createAnimatedGradientPath(
    points: List<Offset>,
    size: Size,
    progress: Float
): Path {
    return Path().apply {
        if (points.isNotEmpty()) {
            val animatedPoints = points.take((points.size * progress).toInt() + 1)
            moveTo(animatedPoints.first().x, size.height)
            animatedPoints.forEach { point ->
                lineTo(point.x, point.y)
            }
            lineTo(animatedPoints.last().x, size.height)
            close()
        }
    }
}

private fun DrawScope.drawTooltip(
    point: Offset,
    value: Float,
    label: String,
    textPaint: Paint
) {
    val tooltipWidth = 120.dp.toPx()
    val tooltipHeight = 50.dp.toPx()
    val tooltipPadding = 10.dp.toPx()

    val tooltipX = (point.x - tooltipWidth/2).coerceIn(0f, size.width - tooltipWidth)
    val tooltipY = (point.y - tooltipHeight - tooltipPadding).coerceIn(0f, size.height - tooltipHeight)

    drawRoundRect(
        color = Color.DarkGray.copy(alpha = 0.9f),
        topLeft = Offset(tooltipX, tooltipY),
        size = Size(tooltipWidth, tooltipHeight),
        cornerRadius = CornerRadius(8.dp.toPx())
    )

    drawContext.canvas.nativeCanvas.apply {
        drawText(
            "Price: ${value.toString().formatVolume()}",
            tooltipX + tooltipPadding,
            tooltipY + 20.dp.toPx(),
            textPaint
        )
        drawText(
            "Time: $label",
            tooltipX + tooltipPadding,
            tooltipY + 40.dp.toPx(),
            textPaint
        )
    }
}

private fun calculateYAxisLabels(dataPoints: List<Float>): List<Float> {
    if (dataPoints.isEmpty()) return emptyList()
    val max = dataPoints.maxOrNull() ?: 0f
    val min = dataPoints.minOrNull() ?: 0f
    val step = (max - min) / 4
    return List(5) { min + it * step }.reversed()
}