package com.canlioya.dynamicfontsizes

import androidx.lifecycle.ViewModel
import com.canlioya.dynamicfontsizes.fontsizeprefs.FontSizePrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: AppPreferences,
): ViewModel() {

    private val _fontSizeChoice =
        MutableStateFlow(FontSizePrefs.getFontPrefFromKey(preferences.getFontSizePref()))
    val fontSizeChoices: StateFlow<FontSizePrefs> = _fontSizeChoice

    fun setFontSize(fontSizePrefs: FontSizePrefs) {
        _fontSizeChoice.value = fontSizePrefs
        preferences.setFontSizePref(fontSizePrefs.key)
    }
}