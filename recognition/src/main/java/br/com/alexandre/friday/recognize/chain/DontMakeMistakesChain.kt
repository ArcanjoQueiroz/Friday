package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class DontMakeMistakesChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        var response: String = if (request?.name == null) {
            "Eu não cometo erros!"
        } else {
            String.format("Eu não cometo erros, %s!", request.name);
        }
        return Arrays.asList(response)
    }


    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("você") && request.q!!.contains("errou")
    }
}