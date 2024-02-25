package br.com.lucas.alugames.servicos

import br.com.lucas.alugames.modelo.InfoJogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class BuscaInfo {
    fun buscaJogo(id:String): InfoJogo? {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()
        println(json)

        //inicializando gson
        val gson = Gson()

        var meuInfoJogo: InfoJogo? = null
        val tenta = runCatching {meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)  }
        tenta.onFailure { println("tente outro id") }

        return meuInfoJogo
    }

}