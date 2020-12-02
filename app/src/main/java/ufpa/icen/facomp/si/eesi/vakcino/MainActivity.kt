package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import ufpa.icen.facomp.si.eesi.vakcino.BD
import ufpa.icen.facomp.si.eesi.vakcino.BD.Companion.dados
import ufpa.icen.facomp.si.eesi.vakcino.ui.login.TelaLogin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adicionarUsuario = findViewById<Button>(R.id.adicionar_usuario)
        val listaUsuarios = findViewById<ListView>(R.id.lista)

        TelaLogin.primeiroCadastro=false

        val bd = BD()
        var dados:ArrayList<String> = ArrayList<String>()
        val tamanho:Int = bd.getTamanhoDados()

        if(tamanho>0) {
            for (i in 0..tamanho-1) {
                dados.add(bd.getInfoUsuario(i).get(0).toString())
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dados)

            listaUsuarios.setAdapter(adapter)
        }

        listaUsuarios.setOnItemClickListener{ parent, view, position, id ->
            var lista:ArrayList<String> = bd.getInfoUsuario(position)
            Toast.makeText(this, lista.toString(), Toast.LENGTH_SHORT).show()
            val i = Intent(this,TelaInfoUsuario::class.java)
            i.putExtra("Posicao do item da lista",position)
            startActivity(i)
            finish()
        }

        adicionarUsuario.setOnClickListener {
            val i = Intent(this,TelaCadastro::class.java)
            startActivity(i)
            finish()
        }

    }
}


