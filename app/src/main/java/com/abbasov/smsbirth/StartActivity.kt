package com.abbasov.smsbirth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        val anim2 = AnimationUtils.loadAnimation(this,R.anim.anim)
        text_compass.startAnimation(anim2)
        val anim= AnimationUtils.loadAnimation(this,R.anim.anim)
        gif.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }
}