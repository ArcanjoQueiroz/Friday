package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class StreetChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        var response = if (request?.subThoroughfare == null || request.subThoroughfare.trim { it <= ' ' }.isEmpty()) {
            String.format("Você está na %s", request?.thoroughfare)
        } else {
            String.format("Você está na %s, número %s", request.thoroughfare, request.subThoroughfare)
        }
        return Arrays.asList(response)
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("qual") &&
                request.q!!.contains("é") &&
                request.q!!.contains("essa") &&
                request.q!!.contains("rua")
                ||
                request.q!!.contains("que") &&
                request.q!!.contains("rua") &&
                request.q!!.contains("é") &&
                request.q!!.contains("essa")
    }
}