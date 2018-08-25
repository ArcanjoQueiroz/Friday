package br.com.alexandre.friday.recognize.chain;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

import java.util.Arrays;
import java.util.Collection;

public class HelloChain extends RecognizerChain {

    public Collection<String> execute(RecognizerRequest request) {
        return Arrays.asList(String.format("Olá, %s! Em que posso ser útil?", request.getName()));
    }

    public boolean apply(RecognizerRequest request) {
        return (request.getQ().contains("olá"));
    }
}
