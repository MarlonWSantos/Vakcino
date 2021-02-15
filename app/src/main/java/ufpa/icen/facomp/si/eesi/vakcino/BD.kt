package ufpa.icen.facomp.si.eesi.vakcino


import android.widget.Toast
import java.util.*

class BD {

    companion object{
       var dados: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()
       var dadosVacina: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()
       var vacinasCriancas: ArrayList<String> = ArrayList<String>()
       var vacinasAdultos: ArrayList<String> = ArrayList<String>()
    }

    fun getInfoUsuario(index:Int):ArrayList<String>{
        return dados.get(index)
    }

    fun setInfoUsuario(nome:String,idade:String,sexo:String,parentesco:String){
        dados.add(arrayListOf(nome,idade,sexo,parentesco))
    }

    fun getInfoVacina(): ArrayList<ArrayList<String>> {
        return dadosVacina
    }

    fun setInfoVacina(vacina:String,data:String,local:String,index:Int){
        val id:String = index.toString()
        dadosVacina.add(arrayListOf(id,vacina,data,local))
    }

    fun getTamanhoDados():Int{
        return dados.size
    }

    fun isDadosVacinaNaoVazio(): Boolean {
        return !dadosVacina.isNullOrEmpty()
    }

    fun editarUsuario(index:Int,nome:String,idade:String,sexo:String,parentesco:String){
        dados.get(index).set(0,nome)
        dados.get(index).set(1,idade)
        dados.get(index).set(2,sexo)
        dados.get(index).set(3,parentesco)
    }

    fun excluirUsuario(index:Int){

        var temp : ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()
        temp = ArrayList(dadosVacina)

        for(lista in dadosVacina){
            if(lista.get(0) == index.toString()){
                temp.remove(lista)
            }
        }

        dadosVacina.clear()
        dadosVacina = ArrayList(temp)

        dados.removeAt(index)

        var number=0

        for(lista in dadosVacina){

            number=lista.get(0).toInt()

            if(number > index){
                number=number-1
                lista.set(0,number.toString())
            }
        }
    }

    fun carregaListaVacinas(tipoPessoa:String):ArrayList<String>{

        if(tipoPessoa.equals("crianca")) {
            vacinasCriancas = arrayListOf(
                "BCG",
                "Hepatite B",
                "Difteria",
                "Tétato",
                "Coqueluche",
                "Meningite",
                "Poliomielite",
                "Febre amarela",
                "Sarampo",
                "Rubéola",
                "Caxumba"
            )
            return vacinasCriancas
        }
        vacinasAdultos = arrayListOf(
            "Difteria",
            "Tétano",
            "Febre Amarela",
            "Sarampo",
            "Caxumba",
            "Rubéola",
            "Dengue",
            "Pneumonia")
        return vacinasAdultos
    }
}







