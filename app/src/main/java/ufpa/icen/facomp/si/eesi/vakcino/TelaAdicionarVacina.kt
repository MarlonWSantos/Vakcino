package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ufpa.icen.facomp.si.eesi.vakcino.ui.login.TelaLogin

class TelaAdicionarVacina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_adicionar_vacina)

        val voltar = findViewById<Button>(R.id.voltar)


        voltar.setOnClickListener{

            var novoCadastro = TelaLogin.novoCadastro
            var i = Intent()
            if(novoCadastro){
                i = Intent(this, TelaCadastro::class.java)
            }else{
                i = Intent(this,TelaInfoUsuario::class.java)
                i.putExtra("Posicao do item da lista",TelaInfoUsuario.index)
            }

            startActivity(i)
            finish()
        }
    }
}