package ufpa.icen.facomp.si.eesi.vakcino

class BD {
    companion object{
       var dados: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()

    }

    fun getInfoUsuario(index:Int):ArrayList<String>{
        return dados.get(index)
    }

    fun setInfoUsuario(nome:String,idade:String,sexo:String,parentesco:String){
        dados.add(arrayListOf(nome,idade,sexo,parentesco))
    }





}







