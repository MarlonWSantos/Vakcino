package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class TelaInfoUsuario : AppCompatActivity() {
    companion object{
        var index:Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_info_usuario)

        val intent:Intent = getIntent()
        index = intent.getSerializableExtra("Posicao do item da lista") as Int

        val infoUsuario = findViewById<TextView>(R.id.infoUsuario)
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

        val bd = BD()
        var info:ArrayList<String> = bd.getInfoUsuario(index)
        var dadosUsuario:String = "Nome: "+info.get(0)+"\nIdade: "+info.get(1)+"\nSexo: "+info.get(2)+"\nParentesco: "+info.get(3)
        infoUsuario.setText(dadosUsuario)

        var dadosVacina:ArrayList<String> = ArrayList<String>()
        val tamanhoVacinas:Int = bd.getTamanhoDadosVacina()

        if(tamanhoVacinas>0) {
            for (i in 0..tamanhoVacinas-1) {
                dadosVacina.add(bd.getInfoVacina(i).get(0).toString())
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dadosVacina)
            listaVacinas.setAdapter(adapter)
        }
    }
}
