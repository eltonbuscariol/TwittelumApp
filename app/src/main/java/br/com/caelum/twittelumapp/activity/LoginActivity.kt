package br.com.caelum.twittelumapp.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.modelo.Usuario
import br.com.caelum.twittelumapp.viewmodel.Injetor
import br.com.caelum.twittelumapp.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this, Injetor).get(UsuarioViewModel::class.java)

        viewModel.usuarioLogado().observe(this, Observer { estaLogado ->
            estaLogado?.let {
                if(estaLogado){
                    vaiParaMain()
                }
            }
        })

        viewModel.falha().observe(this, Observer { erro ->
            erro?.let {
                Toast.makeText(this, erro.message, Toast.LENGTH_SHORT).show()
            }
        })

        login_criar.setOnClickListener { viewModel.criaLogin(usuarioDaTela()) }

        login_entrar.setOnClickListener { viewModel.login(usuarioDaTela()) }
    }

    private fun vaiParaMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun usuarioDaTela(): Usuario {
        val nome = login_campoNome.text.toString()
        val senha = login_campoSenha.text.toString()
        val username = login_campoUsername.text.toString()
        return Usuario(nome = nome, senha = senha, username = username)
    }
}
