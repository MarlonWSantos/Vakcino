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

    fun getInfoVacina(): ArrayList<ArrayList<String>> {
        return dadosVacina
    }

    fun setInfoVacina(vacina:String,data:String,local:String,index:Int){
        var id:String = index.toString()
        dadosVacina.add(arrayListOf(id,vacina,data,local))
    }

    fun getTamanhoDados():Int{
        return dados.size
    }

    fun getTamanhoDadosVacina(): Boolean {
        return dadosVacina.isNullOrEmpty()
    }



}







