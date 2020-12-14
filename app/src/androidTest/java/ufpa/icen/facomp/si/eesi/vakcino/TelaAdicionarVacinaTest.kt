package ufpa.icen.facomp.si.eesi.vakcino

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import ufpa.icen.facomp.si.eesi.vakcino.ui.login.TelaLogin

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TelaAdicionarVacinaTest {
    private lateinit var nome: String
    private lateinit var data: String
    private lateinit var sexo: String
    private lateinit var parentesco: String
    private lateinit var idade: String



    @get:Rule
    var activityRule = ActivityTestRule(TelaLogin::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("ufpa.icen.facomp.si.eesi.vakcino", appContext.packageName)
    }
        @Before
        fun criandoUmUsuarioAntesDoTest() {
            nome = "Pedro Luis"
            data = "02/05/2020"
            idade = "35 anos"
            parentesco = "Filho(a)"

            onView(withId(R.id.cadastro))
                .perform(click())
            onView(withId(R.id.inputNome))
                .perform(typeText(nome),ViewActions.closeSoftKeyboard())
            onView(withId(R.id.inputIdade))
                .perform(typeText(idade),ViewActions.closeSoftKeyboard())
            onView(withId(R.id.Masculino))
                .perform(click())
            onView(withId(R.id.comboParentesco)).perform(click())
            onView(withText(parentesco)).perform(click())
            onView(withId(R.id.salvar))
                .perform(click())
            onView(withText(nome)).perform(click())
            onView(withId(R.id.adicionar_vacina)).perform(click())
        }

    @After
    fun limpaDadosTest(){
        BD.dados.clear()
        BD.dadosVacina.clear()
    }

    @Test
    fun clickComboVacinaTest(){
        onView(withId(R.id.comboVacinas)).perform(click())
        onView(withText("Gripe")).perform(click())
        onView(withId(R.id.salvar)).perform(click())
        onView(allOf(withText("Gripe"), withParent(withId(R.id.listaVacinas))));
    }

    @Test
    fun nenhumClickComboVacinaTest(){
        onView(withId(R.id.salvar)).perform(click())
        onView(allOf(withText("Gripe"), withParent(withId(R.id.listaVacinas))));
    }

    @Test
    fun insereDataTest(){
        onView(withId(R.id.inputData))
            .perform(typeText("05/12/2020"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.salvar)).perform(click())
    }

    @Test
    fun insereTextoEmDataTest(){
        onView(withId(R.id.inputData))
            .perform(typeText("Texto"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.salvar)).perform(click())
    }

    @Test
    fun insereDiaComTresDigitosEmDataTest(){
        onView(withId(R.id.inputData))
            .perform(typeText("050/12/2020"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.salvar)).perform(click())
    }

    @Test
    fun insereMesComTresDigitosEmDataTest(){
        onView(withId(R.id.inputData))
            .perform(typeText("05/120/2020"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.salvar)).perform(click())
    }

    @Test
    fun insereAnoComTresDigitosEmDataTest(){
        onView(withId(R.id.inputData))
            .perform(typeText("05/12/202"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.salvar)).perform(click())
    }

    @Test
    fun insereEnderecoEmLocalTest(){
        onView(withId(R.id.inputLocal))
            .perform(typeText("Posto de vacina - Guama"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.salvar)).perform(click())
    }

    @Test
    fun naoInsereEnderecoEmLocalTest(){
        onView(withId(R.id.inputData))
            .perform(click(),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.salvar)).perform(click())
    }

    @Test
    fun clicaNaViewParaAbrirCameraTest(){
        onView(withId(R.id.imageView))
            .perform(click())
    }

    @Test
    fun clicaEmCancelarTest(){
        onView(withId(R.id.cancelar))
            .perform(click())
    }

    @Test
    fun clicaEmSalvarTest(){
        onView(withId(R.id.salvar))
            .perform(click())
    }
}


