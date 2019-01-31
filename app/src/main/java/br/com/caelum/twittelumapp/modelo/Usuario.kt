package br.com.caelum.twittelumapp.modelo

data class Usuario(
    val nome: String,
    val senha: String,
    val username: String,
    val foto: String? = null,
    val id : Long = 0
)