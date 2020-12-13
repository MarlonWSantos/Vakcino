package ufpa.icen.facomp.si.eesi.vakcino

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import ufpa.icen.facomp.si.eesi.vakcino.BD

class BDTest {
    lateinit var bd: BD
    lateinit var nome:String
    lateinit var idade:String
    lateinit var sexo:String
    lateinit var parentesco:String

    @Before
    fun setUp() {
        bd = BD()
        nome = "Pedro Paulo"
        idade = "27 anos"
        sexo = "Masculino"
        parentesco = "Pai"
        bd.setInfoUsuario(nome,idade,sexo,parentesco)
    }

    @After
    fun tearDown() {
        nome = ""
        idade = ""
        sexo = ""
        parentesco = ""
        BD.dados.clear()

    }

    @Test
    fun getInfoUsuario() {
        assertEquals("[Pedro Paulo, 27 anos, Masculino, Pai]",bd.getInfoUsuario(0).toString())
    }

    @Test
    fun setInfoUsuario() {
        assertFalse("Retorna True se não há dados salvo," +
                "ou False se estiver com dados salvo.",BD.dados.isEmpty())
    }

    @Test
    fun getTamanhoDados() {
        assertEquals(1,bd.getTamanhoDados())
    }
}