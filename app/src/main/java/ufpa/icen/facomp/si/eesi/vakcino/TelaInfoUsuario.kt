package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

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
        val linhaTempo = findViewById<Button>(R.id.linha_tempo)
        val editar = findViewById<Button>(R.id.editar)
        val excluir = findViewById<Button>(R.id.excluir)
        val listaVacinas = findViewById<ListView>(R.id.listaVacinas)


        val bd = BD()
        var info:ArrayList<String> = bd.getInfoUsuario(index)
        var dadosUsuario:String = "Nome: "+info.get(0)+"\nIdade: "+info.get(1)+"\nSexo: "+info.get(2)+"\nParentesco: "+info.get(3)
        infoUsuario.setText(dadosUsuario)

        val tamanhoVacinas: Boolean = bd.getTamanhoDadosVacina()

        if(!tamanhoVacinas) {
            var dadosVacina:ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()
            dadosVacina=bd.getInfoVacina()

            var lista: ArrayList<String> = ArrayList<String>()

            for (i in 0..(dadosVacina.size)-1) {
                if(dadosVacina.get(i).get(0)==index.toString()){
                    lista.add(dadosVacina.get(i).get(1).toString())
                }
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
            listaVacinas.setAdapter(adapter)
        }

        excluir.setOnClickListener{
            val nome = BD.dados.get(index).get(1)
            bd.excluirUsuario(index)

            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }

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

        linhaTempo.setOnClickListener{
            val i = Intent(this,TelaLinhaDoTempoDeVacinas::class.java)
            i.putExtra("Posicao do item da lista",index)
            startActivity(i)
            finish()
        }

        editar.setOnClickListener{
            val i = Intent(this, TelaEditarUsuario::class.java)
            i.putExtra("Posicao do item da lista",index)
            i.putExtra("Dados do usuario",info)
            startActivity(i)
            finish()
        }
    }
}



