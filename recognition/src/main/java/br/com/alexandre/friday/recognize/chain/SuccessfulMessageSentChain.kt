package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class SuccessfulMessageSentChain: RecognizerChain(){
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        return Arrays.asList("Mensagem enviada com sucesso!");
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("mensagem")
    }
}