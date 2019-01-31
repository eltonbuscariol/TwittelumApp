package br.com.caelum.twittelumapp.webclient

import br.com.caelum.twittelumapp.modelo.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST


class UsuarioWebClient(private val retrofit: Retrofit) {

    private val service = retrofit.create(UsuarioService::class.java)

    fun cria(
        usuario: Usuario,
        sucesso : (usuario : Usuario) -> Unit,
        falha : (erro : Throwable) -> Unit){

        service.cria(usuario).enqueue(UsuarioCallback(sucesso, falha))
    }

    fun login(
        usuario: Usuario,
        sucesso : (usuario : Usuario) -> Unit,
        falha : (erro : Throwable) -> Unit){

        service.loga(usuario).enqueue(UsuarioCallback(sucesso, falha))
    }

    private interface UsuarioService{
        @POST("/usuario")
        fun cria(@Body usuario: Usuario):Call<Usuario>

        @POST("/usuario/login")
        fun loga(@Body usuario: Usuario) : Call<Usuario>
    }

    private class UsuarioCallback(
        private val sucesso: (usuario: Usuario) -> Unit,
        private val falha: (erro: Throwable) -> Unit)
        : Callback<Usuario> {

        override fun onFailure(call: Call<Usuario>, t: Throwable) {
            falha(t)
        }

        override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
            response.body()?.let(sucesso)
        }

    }
}