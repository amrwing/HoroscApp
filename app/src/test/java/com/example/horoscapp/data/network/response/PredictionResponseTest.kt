package com.example.horoscapp.data.network.response

import com.example.horoscapp.motherobject.HoroscopeMotherObject
import io.kotlintest.shouldBe
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PredictionResponseTest{

    @Test
    fun `toDomain should return a correct PredictionModel`(){
        //Given
        val predictionResponse = HoroscopeMotherObject.anyResponse.copy(sign = "libra")
        //When
        val predictionModel = predictionResponse.toDomain()
        //Then
        predictionModel.sign shouldBe  predictionResponse.sign
        predictionModel.horoscope shouldBe predictionResponse.horoscope
    }
}