package com.dicoding.kotlin.submission2githubuser.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.kotlin.submission2githubuser.MainActivity
import com.dicoding.kotlin.submission2githubuser.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(object : Runnable {
            override fun run() {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            }

        }, 1500)

    }
}