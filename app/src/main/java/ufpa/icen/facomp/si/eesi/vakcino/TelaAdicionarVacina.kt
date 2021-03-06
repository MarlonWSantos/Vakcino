package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import ufpa.icen.facomp.si.eesi.vakcino.ui.login.TelaLogin
//6
class TelaAdicionarVacina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_adicionar_vacina)

        val cancelar = findViewById<Button>(R.id.cancelar)
        val salvar = findViewById<Button>(R.id.salvar)
        val criarLembrete = findViewById<Button>(R.id.criar_lembrete)
        val comboVacinas = findViewById<Spinner>(R.id.comboVacinas)
        val inputData = findViewById<TextInputEditText>(R.id.inputData)
        val inputLocal = findViewById<TextInputEditText>(R.id.inputLocal)

/*        var lista = arrayOf("Hepatite B","Pneumonia","Febre Amarela","HPV","Herpes-zóster","Gripe",
            "Tétano","Difteria","Coqueluche","Sarampo","Caxumba","Rubéola","Dengue")*/
        var lista = arrayListOf(
            "Difteria",
            "Tétano",
            "Febre Amarela",
            "Sarampo",
            "Caxumba",
            "Rubéola",
            "Dengue",
            "Pneumonia")

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista)
        comboVacinas.setAdapter(adapter)

        cancelar.setOnClickListener{
            mudarParaOutraTela()
        }

        salvar.setOnClickListener{
            var vacina:String = comboVacinas.getSelectedItem().toString()
            var data:String = inputData.text.toString()
            var local:String = inputLocal.text.toString()

            if(!(data == " " || local == " " || vacina == " ")){
                val bd = BD()
                bd.setInfoVacina(vacina, data, local,TelaInfoUsuario.index)

                mudarParaOutraTela()
            }
        }



        criarLembrete.setOnClickListener{
            var i = Intent(this,TelaCriarLembrete::class.java)
            startActivity(i)
            finish()
        }
    }
    fun mudarParaOutraTela(){
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