package br.com.lucas.alugames.modelo

data class Jogo(val titulo: String, val capa: String) {
     var descricao:String?= null


    override fun toString(): String {
        return "\n Meu br.com.lucas.alugames.modelo.Jogo:\n" +
                " Título: $titulo \n" +
                " Capa: $capa \n" +
                " Descricao: $descricao"
    }

}