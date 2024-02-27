package br.com.lucas.alugames.principal

import br.com.lucas.alugames.modelo.Gamer

fun main(){
    val gamer1= Gamer("Jaque", "jaque100@alura.com")

    gamer1.let{
        it.dataNascimento = "20/10/1996"
        it.userName = "jaqueSkywalker"

    }.also { println(gamer1) }



    val gamer2 = Gamer("Jeni", "juni200@alura.com", "20/10/1999", "jeni100" )

    gamer1.let {
        it.userName = "jack"
    }.also { println(gamer1
    ) }
    println(gamer2)



}


