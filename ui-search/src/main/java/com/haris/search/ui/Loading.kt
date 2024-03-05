package com.haris.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.haris.compose.shimmerLoadingAnimation

@Composable
internal fun Loading() {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp)
                .background(color = Color.LightGray)
                .shimmerLoadingAnimation()
        )
        Spacer(modifier = Modifier.height(32.dp))
        repeat(3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .height(30.dp)
                        .width(200.dp)
                        .background(color = Color.LightGray)
                        .shimmerLoadingAnimation()
                )
                Box(
                    Modifier
                        .height(30.dp)
                        .width(100.dp)
                        .background(color = Color.LightGray)
                        .shimmerLoadingAnimation()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) {
                    Box(
                        Modifier
                            .size(250.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = Color.LightGray)
                            .shimmerLoadingAnimation()
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}