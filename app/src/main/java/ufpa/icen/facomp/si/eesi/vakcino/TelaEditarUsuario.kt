package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class TelaEditarUsuario : AppCompatActivity() {
    companion object{
        var index:Int = 0
        var infoUsuario:ArrayList<String> = ArrayList<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_editar_usuario)

        val cancelar = findViewById<Button>(R.id.cancelar)
        val salvar = findViewById<Button>(R.id.salvar)
        val comboParentesco = findViewById<Spinner>(R.id.comboParentesco)
        val inputNome = findViewById<TextInputEditText>(R.id.inputNome)
        val inputIdade = findViewById<TextInputEditText>(R.id.inputIdade)
        val sexoRadioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        val intent: Intent = getIntent()
        TelaInfoUsuario.index = intent.getSerializableExtra("Posicao do item da lista") as Int
        infoUsuario = intent.getSerializableExtra("Dados do usuario") as ArrayList<String>

        var lista = arrayOf("Pai","Mãe","Filho(a)","Irmã(o)","Tio(a)","Avô(ó)","Neto(a)","Sobrinho(a)")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista)
        comboParentesco.setAdapter(adapter)

        val tamanho = comboParentesco.getAdapter().getCount()

        for(i in 0..tamanho-1){
            if(comboParentesco.getItemAtPosition(i).toString() == infoUsuario.get(3)){
                comboParentesco.setSelection(i)
            }
        }

        if(infoUsuario.get(2) == "Masculino"){
            sexoRadioGroup.check(R.id.Masculino)
        }else if(infoUsuario.get(2) == "Feminino"){
            sexoRadioGroup.check(R.id.Feminino)
        }

        val nome = infoUsuario.get(0)
        val idade = infoUsuario.get(1)

        inputNome.setText(nome)
        inputIdade.setText(idade)




        cancelar.setOnClickListener{
            val i = Intent(this, TelaInfoUsuario::class.java)
            i.putExtra("Posicao do item da lista", index)
            startActivity(i)
            finish()
        }

        salvar.setOnClickListener{

            var nome:String = inputNome.text.toString()
            var idade:String = inputIdade.text.toString()
            var id: Int = sexoRadioGroup.checkedRadioButtonId
            var sexo:String

            if(id != -1) {
                val radio:RadioButton = findViewById(id)
                sexo=radio.text.toString()
            }else{
                sexo=""
            }

            var parentesco:String = comboParentesco.getSelectedItem().toString()

            if(!(nome == " " || idade == " " || sexo == " " || parentesco == " ")){
                val bd = BD()
                bd.editarUsuario(index,nome, idade, sexo, parentesco)

                val i = Intent(this, TelaInfoUsuario::class.java)
                i.putExtra("Posicao do item da lista", index)
                startActivity(i)
                finish()
            }else{
                Toast.makeText(this, "Digite em todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

    }
}