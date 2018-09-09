package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerRequest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DontMakeMistakesChainTest {

    private lateinit var dontMakeMistakesChain: DontMakeMistakesChain

    @Before
    fun setUp() {
        this.dontMakeMistakesChain = DontMakeMistakesChain()
    }

    @Test
    fun shouldSayThatIdontMakeMistakesAlexandre() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"

        val response: MutableCollection<String> = dontMakeMistakesChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("Eu não cometo erros, Alexandre!", response.firstOrNull())
    }

    @Test
    fun shouldSayThatIdontMakeMistakes() {
        val response: MutableCollection<String> = dontMakeMistakesChain.execute(RecognizerRequest())

        assertEquals(1, response.size)
        assertEquals("Eu não cometo erros!", response.firstOrNull())
    }
}
