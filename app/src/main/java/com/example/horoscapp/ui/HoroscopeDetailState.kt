package com.example.horoscapp.ui

import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.PredictionModel

sealed class HoroscopeDetailState {
    data object Loading:HoroscopeDetailState()
    data class Error(val error:String):HoroscopeDetailState()
    data class Success(val prediction:String, val sign:String, val horoscopeModel: HoroscopeModel):HoroscopeDetailState()

}