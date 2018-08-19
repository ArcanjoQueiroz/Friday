package br.com.alexandre.friday.recognize.chain;

import java.util.Arrays;
import java.util.Collection;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

public class SuccessfulMessageSentChain extends RecognizerChain {
    public Collection<String> execute(RecognizerRequest request) {
        return Arrays.asList("Mensagem enviada com sucesso!");
    }

    public boolean apply(RecognizerRequest request) {
        return (request.getQ().contains("mensagem") && request.getQ().contains("mãe"));
    }
}
