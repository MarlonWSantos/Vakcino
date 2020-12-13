package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TelaCriarLembrete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_criar_lembrete)

        val cancelar = findViewById<Button>(R.id.cancelar)

        cancelar.setOnClickListener{
            var i = Intent(this,TelaAdicionarVacina::class.java)
            startActivity(i)
            finish()
        }
    }
}