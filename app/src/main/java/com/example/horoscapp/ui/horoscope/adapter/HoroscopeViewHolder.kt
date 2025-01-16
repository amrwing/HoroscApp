package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo:HoroscopeInfo, onItemSelected:(HoroscopeInfo)->Unit){

            binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.text = binding.tvHoroscope.context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, newLambda = {onItemSelected(horoscopeInfo)})
            //onItemSelected(horoscopeInfo)//
        }
    }

    private fun startRotationAnimation(view:View, newLambda:()->Unit) {
    view.animate().apply {
        duration = 500
        interpolator = LinearInterpolator()
        rotationBy(360f)
        withEndAction{
        newLambda()
        }
        start()
    }
    }
}