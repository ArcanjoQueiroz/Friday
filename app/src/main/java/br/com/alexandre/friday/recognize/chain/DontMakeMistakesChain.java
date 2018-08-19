package br.com.alexandre.friday.recognize.chain;

import java.util.Arrays;
import java.util.Collection;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

public class DontMakeMistakesChain extends RecognizerChain {
    public Collection<String> execute(RecognizerRequest request) {
        return Arrays.asList(String.format("Eu não cometo erros, %s!", request.getName()));
    }

    public boolean apply(RecognizerRequest request) {
        return request.getQ().contains("você") &&
                request.getQ().contains("errou");
    }
}
