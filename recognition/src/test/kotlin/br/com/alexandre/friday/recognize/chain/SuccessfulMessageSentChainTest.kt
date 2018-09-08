package br.com.alexandre.friday.recognize.chain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SuccessfulMessageSentChainTest {

    private lateinit var successfulMessageSentChain: SuccessfulMessageSentChain

    @Before
    fun setUp() {
        this.successfulMessageSentChain = SuccessfulMessageSentChain()
    }

    @Test
    fun shouldSayTheMessageWasSentWithSucess() {
        val response: MutableCollection<String> = successfulMessageSentChain.execute(RecognizerRequest())

        assertEquals(1, response.size)
        assertEquals("Mensagem enviada com sucesso!", response.firstOrNull())
    }
}
