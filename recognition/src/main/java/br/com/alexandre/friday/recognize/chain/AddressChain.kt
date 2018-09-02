package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class AddressChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        return Arrays.asList(String.format("Você está no bairro %s da cidade de %s, endereço %s, número %s",
                request?.subLocality,
                request?.locality,
                request?.thoroughfare,
                request?.subThoroughfare))
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("qual") &&
                request.q!!.contains("meu") &&
                request.q!!.contains("endereço")
                ||
                request.q!!.contains("qual") &&
                request.q!!.contains("minha") &&
                request.q!!.contains("localização")
    }
}