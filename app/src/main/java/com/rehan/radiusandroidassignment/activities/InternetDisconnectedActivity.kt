package com.rehan.radiusandroidassignment.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rehan.radiusandroidassignment.R
import com.rehan.radiusandroidassignment.databinding.ActivityInternetDisconnectedBinding
import com.rehan.radiusandroidassignment.utils.ConnectionLiveData

class InternetDisconnectedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInternetDisconnectedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_internet_disconnected)

        val cld = ConnectionLiveData(application)
        isConnected(cld)

    }

    private fun isConnected(cld: ConnectionLiveData) {
        cld.observe(this) { isConnected ->

            if (isConnected) {
                startActivity(Intent(this, SplashScreenActivity::class.java))
            }
        }
    }

}