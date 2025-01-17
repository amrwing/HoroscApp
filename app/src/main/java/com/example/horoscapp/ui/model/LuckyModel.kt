package com.example.horoscapp.ui.model

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import javax.inject.Inject

data class LuckyModel @Inject constructor(@DrawableRes val image: Int, @StringRes val text: Int) {}