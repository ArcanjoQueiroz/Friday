package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class YouReWelcomeChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        var response: String = if (request?.name == null) {
            "De nada!"
        } else {
            String.format("De nada, %s!", request.name);
        }
        return Arrays.asList(response)
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("obrigado") || request.q!!.contains("obrigada")
    }

}