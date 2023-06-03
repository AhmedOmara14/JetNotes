package com.omaradev.jetnote.ui.all_notes

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerNoteItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit
) {
    if (isLoading) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 7.dp, end = 8.dp, bottom = 1.dp)
                .shadow(1.dp, RoundedCornerShape(4.dp))
                .background(Color.White, RoundedCornerShape(4.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp)
                    .clip(shape = CircleShape)
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Column(Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                        .shimmerEffect()
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(20.dp)
                    .shimmerEffect()
            )
        }
    } else contentAfterLoading()
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffSetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFDBDBDB),
                Color(0xFFD3D2D2),
                Color(0xFFCACACA)
            ),
            start = Offset(startOffSetX, 0f),
            end = Offset(startOffSetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}