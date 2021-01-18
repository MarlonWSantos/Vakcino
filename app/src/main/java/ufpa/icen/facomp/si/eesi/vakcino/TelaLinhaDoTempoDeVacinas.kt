package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class TelaLinhaDoTempoDeVacinas  : AppCompatActivity() {
    companion object{
        var index:Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_linha_do_tempo_de_vacinas)

        val intent:Intent = getIntent()
        index = intent.getSerializableExtra("Posicao do item da lista") as Int

        val voltar = findViewById<Button>(R.id.botaoVoltar)


        voltar.setOnClickListener{
            val i = Intent(this,TelaInfoUsuario::class.java)
            i.putExtra("Posicao do item da lista", index)
            startActivity(i)
            finish()
        }
    }
}