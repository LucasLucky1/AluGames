package br.com.lucas.alugames.modelo

import java.util.*
import kotlin.random.Random

class Gamer(var nome: String, var email: String) {
    var dataNascimento:String? = null
    var userName: String? = null
        set(value) {
            field = value
            if (userId.isNullOrBlank()){
                criaIdInterno()
            }

        }
    var userId: String? = null
        private set

    val jogosBuscados = mutableListOf<Jogo?>()

    constructor(nome:String, email:String, dataNascimento:String, userName:String):
            this(nome,email){
                this.dataNascimento=dataNascimento
                this.userName=userName
                criaIdInterno()
    }

    init {
        validaNome()
        this.email= validaEmail()
    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, userName=$userName, userId=$userId)"
    }

    fun criaIdInterno(){
        val numeroAleatorio = Random.nextInt(10000)
        val tag = String.format("%04d", numeroAleatorio)

        userId="$userName#$tag"
    }


    fun validaEmail():String{
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)){
            return email
        }else throw IllegalArgumentException("Email Inválido")

    }

    fun validaNome(){
        if (nome.isNullOrBlank()){
            throw IllegalArgumentException("Nome Inválido")
        }
    }


    companion object{
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)
            } else {
                return Gamer (nome, email)
            }
        }
    }
}