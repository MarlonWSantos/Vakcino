package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adicionarUsuario = findViewById<Button>(R.id.adicionar_usuario)
        val listaUsuarios = findViewById<ListView>(R.id.lista)

        var lista = arrayOf("Usuário 1","Usuário 2","Usuário 3","Usuário 4")

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,lista)

        listaUsuarios.setAdapter(adapter)

        adicionarUsuario.setOnClickListener {
            val i = Intent(this,TelaCadastro::class.java)
            startActivity(i)
            finish()
        }

    }
}