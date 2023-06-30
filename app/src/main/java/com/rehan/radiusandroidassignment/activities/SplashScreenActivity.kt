package com.rehan.radiusandroidassignment.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rehan.radiusandroidassignment.R
import com.rehan.radiusandroidassignment.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        supportActionBar!!.hide()
        slideAnimation()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    private fun slideAnimation(){
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_slide)
        binding.ivSplash.startAnimation(slideAnimation)
        binding.tvSplash.startAnimation(slideAnimation)
    }

}