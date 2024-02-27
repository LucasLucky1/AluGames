package br.com.lucas.alugames.principal
import br.com.lucas.alugames.modelo.Gamer
import br.com.lucas.alugames.modelo.Jogo
import br.com.lucas.alugames.servicos.BuscaInfo
import java.util.*


//consumindo api
fun main() {
    val leitura = Scanner(System.`in`)
    var gamer1 = Gamer.criarGamer(leitura)
    println("Cadastro concluído com sucesso. Dados do gamer:")
    println(gamer1)

    do{
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

            if (opcao.equals("s", true)){
                println("Digite sua descrição:")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao=descricaoPersonalizada
                println(meuJogo)


            }else {
                meuJogo?.descricao = meuJogo?.titulo
                println(meuJogo)
            }

            gamer1.jogosBuscados.add(meuJogo)
        }
        println("Deseja fazer outra busca? S/N")
        val opcaoRepete = leitura.nextLine()
    }while (opcaoRepete.equals("s",true))



    println("Jogos buscados:")
    println(gamer1.jogosBuscados)
    println("Busca finalizada com sucesso.")


    println("\n Jogos ordenados por título:")
    gamer1.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer1.jogosBuscados.forEach {
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer1.jogosBuscados.filter {
        it?.titulo?.contains("batman",true) ?: false
    }

    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", true)) {
        println("\n Informe a posição do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer1.jogosBuscados.removeAt(posicao)
    }

    println("Lista atualizada:")
    println(gamer1.jogosBuscados)

}