package ufpa.icen.facomp.si.eesi.vakcino

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import ufpa.icen.facomp.si.eesi.vakcino.ui.login.Tela_login


@Suppress("DEPRECATION")
class Tela_inicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        //Handeler para transição automática entre a Tela Inicial e a classe Main
        Handler().postDelayed({
            val i = Intent(this, Tela_login::class.java)
            startActivity(i)
            finish()
            //Espera de 5 segundos antes de trocar de tela
        }, 5000)

    }
}