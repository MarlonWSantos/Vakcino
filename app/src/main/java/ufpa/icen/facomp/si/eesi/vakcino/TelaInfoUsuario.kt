package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class TelaInfoUsuario : AppCompatActivity() {
    companion object{
        var index:Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_info_usuario)

        val intent:Intent = getIntent()
        index = intent.getSerializableExtra("Posicao do item da lista") as Int

        val infoUsuario = findViewById<ListView>(R.id.infoUsuario)
        val voltar = findViewById<Button>(R.id.voltar)
        val adicionarVacina = findViewById<Button>(R.id.adicionar_vacina)
        val listaVacinas = findViewById<ListView>(R.id.listaVacinas)

        voltar.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }

        adicionarVacina.setOnClickListener{
            val i = Intent(this,TelaAdicionarVacina::class.java)
            startActivity(i)
            finish()
        }

        var vacinas = arrayOf("Hepatite B","Pneumonia","Febre Amarela","HPV","Herpes-zóster","Gripe",
            "Tétano","Difteria","Coqueluche","Sarampo","Caxumba","Rubéola","Dengue")
        //var dadosUsuario = arrayOf("Nome: User 1","Idade: 25 anos","Sexo: Masculino","Parentesco: Pai")
        val bd = BD()
        var info:ArrayList<String> = bd.getInfoUsuario(index)
        var dadosUsuario = arrayOf("Nome: "+info.get(0),"Idade: "+info.get(1),"Sexo: "+info.get(2),"Parentesco: "+info.get(3))

        val adapterInfoUser = ArrayAdapter(this,android.R.layout.simple_list_item_1,dadosUsuario)
        val adapterVacinas = ArrayAdapter(this,android.R.layout.simple_list_item_1,vacinas)
        infoUsuario.setAdapter(adapterInfoUser)
        listaVacinas.setAdapter(adapterVacinas)


    }
}
