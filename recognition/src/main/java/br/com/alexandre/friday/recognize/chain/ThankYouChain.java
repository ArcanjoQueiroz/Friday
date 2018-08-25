package br.com.alexandre.friday.recognize.chain;


import java.util.Arrays;
import java.util.Collection;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

public class ThankYouChain extends RecognizerChain {
    public Collection<String> execute(RecognizerRequest request) {
        return Arrays.asList(String.format("De nada, %s!", request.getName()));
    }

    public boolean apply(RecognizerRequest request) {
        return request.getQ().contains("obrigado") || request.getQ().contains("obrigada");
    }
}
