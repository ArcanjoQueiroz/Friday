package br.com.alexandre.friday.recognition.chain

import br.com.alexandre.friday.recognize.RecognizerRequest
import br.com.alexandre.friday.recognize.chain.GoodbyeChain
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class GoodbyeChainTest {
    private lateinit var goodbyeChain: GoodbyeChain

    @Before
    fun setUp() {
        this.goodbyeChain = GoodbyeChain()
    }

    @Test
    fun shouldSayGoodbyeWithName() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"

        val response: MutableCollection<String> = goodbyeChain.execute(request)

        assertEquals(1, response.size)
        assertTrue(response.first().startsWith("Adeus, Alexandre! Que você tenha uma ótima"))
    }

    @Test
    fun shouldSayGoodbyeWithoutName() {
        var request: RecognizerRequest = RecognizerRequest();

        val response: MutableCollection<String> = goodbyeChain.execute(request)

        assertEquals(1, response.size)
        assertTrue(response.first().startsWith("Adeus! Que você tenha uma ótima"))
    }
}