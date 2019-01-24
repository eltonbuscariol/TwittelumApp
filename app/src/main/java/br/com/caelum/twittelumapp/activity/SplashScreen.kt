package br.com.caelum.twittelumapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.caelum.twittelumapp.R

class SplashScreen : AppCompatActivity() {

    private var mDelayHandler : Handler? = null
    private val SPLASH_DELAY : Long = 3000 //3 seconds

    internal val mRunnable : Runnable = Runnable {
        if (!isFinishing){
            val intent = Intent(applicationContext, ListaTweetsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //initialize Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    override fun onDestroy() {

        if (mDelayHandler != null) mDelayHandler!!.removeCallbacks(mRunnable)

        super.onDestroy()

    }
}
