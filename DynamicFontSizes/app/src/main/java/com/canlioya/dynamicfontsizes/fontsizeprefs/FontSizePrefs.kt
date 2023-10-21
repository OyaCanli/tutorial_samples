package com.canlioya.dynamicfontsizes.fontsizeprefs

import androidx.annotation.DrawableRes
import com.canlioya.dynamicfontsizes.R

enum class FontSizePrefs(
    val key: String,
    val fontSizeExtra: Int,
    @DrawableRes val iconRes: Int,
) {
    SMALL("S", -2, R.drawable.ic_font_size_small),
    DEFAULT("M", 0, R.drawable.ic_font_size_default),
    LARGE("L", 2, R.drawable.ic_font_size_large),
    EXTRA_LARGE("XL", 4, R.drawable.ic_font_size_xlarge),
    ;

    companion object {
        fun getFontPrefFromKey(key: String?): FontSizePrefs {
            return FontSizePrefs.values().find {
                it.key == key
            } ?: DEFAULT
        }
    }
}
