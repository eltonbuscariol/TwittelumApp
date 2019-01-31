package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumapp.modelo.Usuario
import br.com.caelum.twittelumapp.repository.UsuarioRepository

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    fun usuarioLogado() = repository.usuarioLogado

    fun usuarioDaSessao() = repository.usuarioDaSessao

    fun falha() = repository.erro

    fun login(usuario: Usuario) = repository.loga(usuario)

    fun criaLogin(usuario: Usuario) = repository.cria(usuario)

    fun desloga() = repository.desloga()

}