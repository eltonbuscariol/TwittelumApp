package br.com.caelum.twittelumapp.repository

import android.arch.lifecycle.MutableLiveData
import br.com.caelum.twittelumapp.modelo.Usuario
import br.com.caelum.twittelumapp.webclient.UsuarioWebClient

class UsuarioRepository(private val client: UsuarioWebClient) {

    val usuarioDaSessao : MutableLiveData<Usuario> = MutableLiveData()
    val erro : MutableLiveData<Throwable> = MutableLiveData()
    val usuarioLogado : MutableLiveData<Boolean> = MutableLiveData()

    fun loga(usuario: Usuario) = client.login(usuario, sucesso(), falha())

    private fun falha() = { falha: Throwable ->
        usuarioLogado.value = false
        erro.value = falha
    }

    fun cria(usuario: Usuario) = client.cria(usuario, sucesso(), falha())

    fun desloga(){
        usuarioLogado.value = false
    }

    private fun sucesso() = { usuario : Usuario ->
        usuarioLogado.value = true
        this.usuarioDaSessao.value = usuario
    }


}
