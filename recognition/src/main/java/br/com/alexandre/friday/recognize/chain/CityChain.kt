package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class CityChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        return Arrays.asList(String.format("Você está na cidade de %s", request?.locality))
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("qual") &&
                request.q!!.contains("é") &&
                request.q!!.contains("essa") &&
                request.q!!.contains("cidade")
                ||
                request.q!!.contains("que") &&
                request.q!!.contains("cidade") &&
                request.q!!.contains("é") &&
                request.q!!.contains("essa")
    }
}