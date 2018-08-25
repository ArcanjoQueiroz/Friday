package br.com.alexandre.friday.recognize.chain;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

import java.util.Arrays;
import java.util.Collection;

public class CityChain extends RecognizerChain {
    public Collection<String> execute(RecognizerRequest request) {
        if (request.getLocality() == null || request.getLocality().trim().isEmpty()) {
            return Arrays.asList(String.format("No momento não consegui verificar a cidade, %s!", request.getName()));
        } else {
            return Arrays.asList(String.format("Você está na cidade de %s", request.getLocality()));
        }
    }

    public boolean apply(RecognizerRequest request) {
        return  (request.getQ().contains("qual") &&
                request.getQ().contains("é") &&
                request.getQ().contains("essa") &&
                request.getQ().contains("cidade"))
                ||
                (request.getQ().contains("que") &&
                        request.getQ().contains("cidade") &&
                        request.getQ().contains("é") &&
                        request.getQ().contains("essa"));
    }
}
