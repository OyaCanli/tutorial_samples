package com.canlioya.dynamicfontsizes

import android.content.SharedPreferences
import com.canlioya.dynamicfontsizes.fontsizeprefs.FontSizePrefs


class AppPreferences(
    private val sharedPreferences: SharedPreferences,
) {

    val editor = sharedPreferences.edit()
    fun getFontSizePref() = sharedPreferences.getString(Keys.FONT_SIZE_PREFS, FontSizePrefs.DEFAULT.key)

    fun setFontSizePref(fontChoiceKey: String) {
        editor.putString(Keys.FONT_SIZE_PREFS, fontChoiceKey).apply()
    }

    companion object {

        // KEYS
        object Keys {
            /**
             * User's preferred font size
             */
            const val FONT_SIZE_PREFS = "fontSizePrefs"
        }
    }
}
