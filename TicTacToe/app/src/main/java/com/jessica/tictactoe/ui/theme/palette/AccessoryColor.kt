package com.jessica.tictactoe.ui.theme.palette

import com.jessica.tictactoe.R

data class AccessoryColor(
    val scale100: Scale,
    val scale200: Scale,
    val scale300: Scale,
    val scale400: Scale,
    val scale500: Scale,
    val scale600: Scale,
    val scale600Contrast: Scale,
    val scale700: Scale,
    val scaleGradStart: Scale,
    val scaleGradEnd: Scale
)

val Colors.red: AccessoryColor
    get() = AccessoryColor(
        scale100 = Scale(R.color.red_100),
        scale200 = Scale(R.color.red_200),
        scale300 = Scale(R.color.red_300),
        scale400 = Scale(R.color.red_400),
        scale500 = Scale(R.color.red_500),
        scale600 = Scale(R.color.red_600),
        scale600Contrast = Scale(R.color.red_600_contrast),
        scale700 = Scale(R.color.red_700),
        scaleGradStart = Scale(R.color.red_grad_start),
        scaleGradEnd = Scale(R.color.red_grad_end),
    )

val Colors.yellow: AccessoryColor
    get() = AccessoryColor(
        scale100 = Scale(R.color.yellow_100),
        scale200 = Scale(R.color.yellow_200),
        scale300 = Scale(R.color.yellow_300),
        scale400 = Scale(R.color.yellow_400),
        scale500 = Scale(R.color.yellow_500),
        scale600 = Scale(R.color.yellow_600),
        scale600Contrast = Scale(R.color.yellow_600_contrast),
        scale700 = Scale(R.color.yellow_700),
        scaleGradStart = Scale(R.color.yellow_grad_start),
        scaleGradEnd = Scale(R.color.yellow_grad_end),
    )


val Colors.cyan: AccessoryColor
    get() = AccessoryColor(
        scale100 = Scale(R.color.cyan_100),
        scale200 = Scale(R.color.cyan_200),
        scale300 = Scale(R.color.cyan_300),
        scale400 = Scale(R.color.cyan_400),
        scale500 = Scale(R.color.cyan_500),
        scale600 = Scale(R.color.cyan_600),
        scale600Contrast = Scale(R.color.cyan_600_contrast),
        scale700 = Scale(R.color.cyan_700),
        scaleGradStart = Scale(R.color.cyan_grad_start),
        scaleGradEnd = Scale(R.color.cyan_grad_end),
    )

val Colors.blue: AccessoryColor
    get() = AccessoryColor(
        scale100 = Scale(R.color.blue_100),
        scale200 = Scale(R.color.blue_200),
        scale300 = Scale(R.color.blue_300),
        scale400 = Scale(R.color.blue_400),
        scale500 = Scale(R.color.blue_500),
        scale600 = Scale(R.color.blue_600),
        scale600Contrast = Scale(R.color.blue_600_contrast),
        scale700 = Scale(R.color.blue_700),
        scaleGradStart = Scale(R.color.blue_grad_start),
        scaleGradEnd = Scale(R.color.blue_grad_end),
    )

val Colors.purple: AccessoryColor
    get() = AccessoryColor(
        scale100 = Scale(R.color.purple_100),
        scale200 = Scale(R.color.purple_200),
        scale300 = Scale(R.color.purple_300),
        scale400 = Scale(R.color.purple_400),
        scale500 = Scale(R.color.purple_500),
        scale600 = Scale(R.color.purple_600),
        scale600Contrast = Scale(R.color.purple_600_contrast),
        scale700 = Scale(R.color.purple_700),
        scaleGradStart = Scale(R.color.purple_grad_start),
        scaleGradEnd = Scale(R.color.purple_grad_end),
    )
