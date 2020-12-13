package ufpa.icen.facomp.si.eesi.vakcino

class BD {
    companion object{
       var dados: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()
       var dadosVacina: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()


    }

    fun getInfoUsuario(index:Int):ArrayList<String>{
        return dados.get(index)
    }

    fun setInfoUsuario(nome:String,idade:String,sexo:String,parentesco:String){
        dados.add(arrayListOf(nome,idade,sexo,parentesco))
    }

    fun getInfoVacina(index:Int):ArrayList<String>{
        return dadosVacina.get(index)
    }

    fun setInfoVacina(vacina:String,data:String,local:String){
        dadosVacina.add(arrayListOf(vacina,data,local))
    }

    fun getTamanhoDados():Int{
        return dados.size
    }

    fun getTamanhoDadosVacina():Int{
        return dadosVacina.size
    }



}







