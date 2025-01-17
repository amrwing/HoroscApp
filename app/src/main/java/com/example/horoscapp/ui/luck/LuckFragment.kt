package com.example.horoscapp.ui.luck

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding:FragmentLuckBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.ivRoulette.setOnClickListener{
            spinRoulette()
        }
    }

    private fun spinRoulette() {
        val random = Random()
        val degrees = random.nextInt(360*4) + 360
        val animator = ObjectAnimator.ofFloat(binding.ivRoulette,View.ROTATION, 0f,degrees.toFloat() )
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.start()
        animator.doOnEnd {
            slideCard()
        }
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(),R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.reversedCard.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
        binding.reversedCard.startAnimation(slideUpAnimation)
    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(),R.anim.grow)
        growAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
               binding.reversedCard.isVisible=false
                showPremonitionView()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        binding.reversedCard.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {
        val appearAnimation = AlphaAnimation(0.0f,1.0f)
        appearAnimation.duration = 1000
        val disappearAnimation = AlphaAnimation(1.0f,0.0f)
        disappearAnimation.duration = 200
        disappearAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true

            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })
        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)

    }
}