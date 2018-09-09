package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerRequest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HelloChainTest {

    private lateinit var helloChain: HelloChain

    @Before
    fun setUp() {
        this.helloChain = HelloChain()
    }

    @Test
    fun shouldSayHelloToAlexandre() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"

        val response: MutableCollection<String> = helloChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("Olá, Alexandre! Em que posso ser útil?", response.firstOrNull())    }

    @Test
    fun shouldSayHello() {
        val response: MutableCollection<String> = helloChain.execute(RecognizerRequest())

        assertEquals(1, response.size)
        assertEquals("Olá! Em que posso ser útil?", response.firstOrNull())
    }
}
