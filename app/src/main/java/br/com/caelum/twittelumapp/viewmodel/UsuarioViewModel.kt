package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumapp.modelo.Usuario
import br.com.caelum.twittelumapp.repository.UsuarioRepository

class UsuarioViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    fun criaLogin(usuario: Usuario) = usuarioRepository.criaLogin(usuario)

    fun login(usuario: Usuario) = usuarioRepository.login(usuario)
}