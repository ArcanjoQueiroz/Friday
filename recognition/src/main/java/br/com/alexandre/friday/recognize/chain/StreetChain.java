package br.com.alexandre.friday.recognize.chain;

import java.util.Arrays;
import java.util.Collection;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

public class StreetChain extends RecognizerChain {
    public Collection<String> execute(RecognizerRequest request) {
        if (request.getSubThoroughfare() == null || request.getSubThoroughfare().trim().isEmpty()) {
            return Arrays.asList(String.format("Você está na %s", request.getThoroughfare()));
        } else {
            return Arrays.asList(String.format("Você está na %s, número %s", request.getThoroughfare(), request.getSubThoroughfare()));
        }
    }

    public boolean apply(RecognizerRequest request) {
        return  (request.getQ().contains("qual") &&
                request.getQ().contains("é") &&
                request.getQ().contains("essa") &&
                request.getQ().contains("rua"))
                ||
                (request.getQ().contains("que") &&
                        request.getQ().contains("rua") &&
                        request.getQ().contains("é") &&
                        request.getQ().contains("essa"));
    }
}
