package br.com.alexandre.friday.recognize.chain;

import java.util.Arrays;
import java.util.Collection;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

public class NeighborhoodChain extends RecognizerChain {
    public Collection<String> execute(RecognizerRequest request) {
        if (request.getLocality() == null || request.getLocality().trim().isEmpty()) {
            return Arrays.asList(String.format("No momento não consegui verificar o bairro, %s!", request.getName()));
        } else {
            return Arrays.asList(String.format("Você está no bairro %s", request.getSubLocality()));
        }
    }

    public boolean apply(RecognizerRequest request) {
        return  (request.getQ().contains("qual") &&
                 request.getQ().contains("é") &&
                 request.getQ().contains("esse") &&
                 request.getQ().contains("bairro"))
                ||
                (request.getQ().contains("que") &&
                 request.getQ().contains("bairro") &&
                 request.getQ().contains("é") &&
                 request.getQ().contains("esse"));
    }
}
