package br.com.alexandre.friday.recognize.chain;

import java.util.Arrays;
import java.util.Collection;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

public class LocationChain extends RecognizerChain {

    public Collection<String> execute(RecognizerRequest request) {
        return Arrays.asList(String.format("Você está na latitude %f e longitude %f",  request.getLatitude(), request.getLongitude()));
    }

    public boolean apply(RecognizerRequest request) {
        return request.getQ().contains("onde") &&
                request.getQ().contains("eu") &&
                request.getQ().contains("estou");
    }
}
