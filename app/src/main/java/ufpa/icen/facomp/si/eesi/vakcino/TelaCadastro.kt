package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import ufpa.icen.facomp.si.eesi.vakcino.ui.login.TelaLogin

class TelaCadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        val cancelar = findViewById<Button>(R.id.cancelar)
        val salvar = findViewById<Button>(R.id.salvar)
        val adicionarVacina = findViewById<Button>(R.id.adicionar_vacina)
        val comboParentesco = findViewById<Spinner>(R.id.comboParentesco)

        var lista = arrayOf("Pai","Mãe","Filho(a)","Irmã(o)","Tio(a)","Avô(ó)","Neto(a)","Sobrinho(a)")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista)
        comboParentesco.setAdapter(adapter)

        cancelar.setOnClickListener {
            var primeiroCadastro = TelaLogin.primeiroCadastro
            var i = Intent()
            if(primeiroCadastro){
                i = Intent(this,TelaLogin::class.java)
            }else{
                i = Intent(this,MainActivity::class.java)
            }
            startActivity(i)
            finish()
        }

        salvar.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }

        adicionarVacina.setOnClickListener{
            val i = Intent(this,TelaAdicionarVacina::class.java)
            startActivity(i)
            finish()
        }



    }
}