package br.com.caelum.twittelumapp.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.fragment.BuscadorDeTweetsFragment
import br.com.caelum.twittelumapp.fragment.ListaTweetsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.menu_tweets -> {
                    exibeFragment(ListaTweetsFragment())
                    true
                }
                R.id.menu_busca ->{
                    exibeFragment(BuscadorDeTweetsFragment())
                    true
                }
                R.id.menu_mapa ->{
                    exibeFragment(Fragment())
                    true
                }
                else -> {
                    true
                }
            }
        }

        bottom_navigation.selectedItemId = R.id.menu_tweets
    }

    private fun exibeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameMain, fragment)
        transaction.commit()
    }
}