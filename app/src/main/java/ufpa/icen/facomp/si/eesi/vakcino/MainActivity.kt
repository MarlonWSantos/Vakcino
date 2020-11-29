package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adicionar_usuario = findViewById<Button>(R.id.adicionar_usuario)

        adicionar_usuario.setOnClickListener {
            val i = Intent(this,TelaCadastro::class.java)
            startActivity(i)
            finish()
        }

    }
}