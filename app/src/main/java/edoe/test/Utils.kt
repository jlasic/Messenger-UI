package edoe.test

import android.content.res.Resources
import android.util.DisplayMetrics
import kotlin.math.ceil
import kotlin.math.floor

fun Float.spToPx(displayMetrics: DisplayMetrics): Int =
    (this * Resources.getSystem().displayMetrics.scaledDensity).round()

fun Float.dpToPx(): Int =
    (this * Resources.getSystem().displayMetrics.density).round()

private fun Float.round(): Int = (if(this < 0) ceil(this - 0.5f) else floor(this + 0.5f)).toInt()