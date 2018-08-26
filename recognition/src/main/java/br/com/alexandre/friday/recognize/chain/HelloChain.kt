package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class HelloChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        var response: String = if (request?.name == null) {
            "Olá! Em que posso ser útil?"
        } else {
            String.format("Olá, %s! Em que posso ser útil?", request.name);
        }
        return Arrays.asList(response)
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("olá")
    }
}