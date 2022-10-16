package com.moriatsushi.insetsx

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

@Stable
internal class UIKeyboardInsets : WindowInsets {
    private var height by mutableStateOf(0)

    override fun getBottom(density: Density): Int {
        return height
    }

    override fun getLeft(density: Density, layoutDirection: LayoutDirection): Int {
        return 0
    }

    override fun getRight(density: Density, layoutDirection: LayoutDirection): Int {
        return 0
    }

    override fun getTop(density: Density): Int {
        return 0
    }

    suspend fun update(height: Int, durationMills: Int) {
        val animatable = Animatable(this.height, Int.VectorConverter)
        try {
            animatable.animateTo(
                targetValue = height,
                animationSpec = tween(durationMills),
            ) {
                this@UIKeyboardInsets.height = value
            }
        } finally {
            this@UIKeyboardInsets.height = height
        }
    }
}