package br.com.alexandre.friday.recognize.chain;

import java.util.Arrays;
import java.util.Collection;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

public class AddressChain extends RecognizerChain {

    public Collection<String> execute(RecognizerRequest request) {
        return Arrays.asList(String.format("Você está no bairro %s da cidade de %s, endereço %s, número %s",
                request.getSubLocality(),
                request.getLocality(),
                request.getThoroughfare(),
                request.getSubThoroughfare()));
    }

    public boolean apply(RecognizerRequest request) {
        return (request.getQ().contains("qual") &&
                request.getQ().contains("meu") &&
                request.getQ().contains("endereço")) ||
                (request.getQ().contains("qual") &&
                request.getQ().contains("minha") &&
                request.getQ().contains("localização"));
    }
}
