package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class NeighborhoodChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        return Arrays.asList(String.format("Você está no bairro %s", request?.subLocality))
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("qual") &&
                request.q!!.contains("é") &&
                request.q!!.contains("esse") &&
                request.q!!.contains("bairro")
                ||
                request.q!!.contains("que") &&
                request.q!!.contains("bairro") &&
                request.q!!.contains("é") &&
                request.q!!.contains("esse")
    }
}