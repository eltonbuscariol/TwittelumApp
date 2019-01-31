package br.com.caelum.twittelumapp.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.fragment.BuscadorDeTweetsFragment
import br.com.caelum.twittelumapp.fragment.ListaTweetsFragment
import br.com.caelum.twittelumapp.viewmodel.Injetor
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import br.com.caelum.twittelumapp.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tweetViewModel by lazy {
        ViewModelProviders.of(this, Injetor).get(TweetViewModel::class.java)
    }
    private val usuarioViewModel by lazy {
        ViewModelProviders.of(this, Injetor).get(UsuarioViewModel::class.java)
    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_sair){
            usuarioViewModel.desloga()
            voltaProLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun voltaProLogin() {
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun exibeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameMain, fragment)
        transaction.commit()
    }
}