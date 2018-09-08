package br.com.alexandre.friday.recognize.chain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class YouReWelcomeChainTest {

    private lateinit var youReWelcomeChain: YouReWelcomeChain

    @Before
    fun setUp() {
        this.youReWelcomeChain = YouReWelcomeChain()
    }

    @Test
    fun shouldSayYouReWelcomeToAlexandre() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"

        val response: MutableCollection<String> = youReWelcomeChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("De nada, Alexandre!", response.firstOrNull())

    }

    @Test
    fun shouldSayYouReWelcome() {
        val response: MutableCollection<String> = youReWelcomeChain.execute(RecognizerRequest())

        assertEquals(1, response.size)
        assertEquals("De nada!", response.firstOrNull())

    }
}
