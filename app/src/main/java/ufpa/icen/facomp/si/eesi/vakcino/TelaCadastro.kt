package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import ufpa.icen.facomp.si.eesi.vakcino.ui.login.TelaLogin
//6
class TelaCadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        val cancelar = findViewById<Button>(R.id.cancelar)
        val salvar = findViewById<Button>(R.id.salvar)
        val comboParentesco = findViewById<Spinner>(R.id.comboParentesco)
        val inputNome = findViewById<TextInputEditText>(R.id.inputNome)
        val inputIdade = findViewById<TextInputEditText>(R.id.inputIdade)
        val sexoRadioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        var lista = arrayOf("Pai","Mãe","Filho(a)","Irmã(o)","Tio(a)","Avô(ó)","Neto(a)","Sobrinho(a)")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista)
        comboParentesco.setAdapter(adapter)

        cancelar.setOnClickListener {
            var novoCadastro = TelaLogin.novoCadastro
            var i = Intent()
            //1
            if(novoCadastro){
                i = Intent(this,TelaLogin::class.java)
            //1
            }else{
                i = Intent(this,MainActivity::class.java)
            }
            startActivity(i)
            finish()
        }

        salvar.setOnClickListener{

            var nome:String = inputNome.text.toString()
            var idade:String = inputIdade.text.toString()
            var id: Int = sexoRadioGroup.checkedRadioButtonId
            var sexo:String =""
            //1
            if(id != -1) {
                val radio:RadioButton = findViewById(id)
                sexo=radio.text.toString()
            }

            var parentesco:String = comboParentesco.getSelectedItem().toString()
            //1
            if(!(nome == " " || idade == " " || sexo == " " || parentesco == " ")){
                val bd = BD()
                bd.setInfoUsuario(nome, idade, sexo, parentesco)

                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()

            }
        }
    }
}