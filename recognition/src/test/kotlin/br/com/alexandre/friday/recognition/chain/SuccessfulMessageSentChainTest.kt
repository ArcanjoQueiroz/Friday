package br.com.alexandre.friday.recognition.chain

import br.com.alexandre.friday.recognize.RecognizerRequest
import br.com.alexandre.friday.recognize.chain.SuccessfulMessageSentChain
import org.junit.Assert
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

        Assert.assertEquals(1, response.size)
        Assert.assertEquals("Mensagem enviada com sucesso!", response.firstOrNull())
    }
}
