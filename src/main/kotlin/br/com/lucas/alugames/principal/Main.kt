package br.com.lucas.alugames.principal
import br.com.lucas.alugames.modelo.Jogo
import br.com.lucas.alugames.servicos.BuscaInfo
import java.util.*


//consumindo api
fun main() {
    val leitura = Scanner(System.`in`)
    println("Qual o id do jogo que gostaria de usar:")
    val jogoEscolhido = leitura.nextLine()
    println(jogoEscolhido)

    val buscaApi = BuscaInfo()
    val informacaoJogo = buscaApi.buscaJogo(jogoEscolhido)



    //inicializando jogo
    var meuJogo: Jogo? = null

    // intruduzindo runCatching, aka try/catch do kotlin
    val resultado = runCatching {

        if (informacaoJogo != null) {
            meuJogo= Jogo(informacaoJogo.info.title,informacaoJogo.info.thumb)
        }

    }

    resultado.onFailure {
        println("tente outro id")

    }

    resultado.onSuccess {
        println("Deseja adicionar uma descrição? S/N")
        val opcao = leitura.nextLine()
''
        if (opcao.equals("s", true)){
            println("Digite sua descrição:")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao=descricaoPersonalizada
            println(meuJogo)


        }else {
            meuJogo?.descricao = meuJogo?.titulo
            println(meuJogo)
        }
    }

}