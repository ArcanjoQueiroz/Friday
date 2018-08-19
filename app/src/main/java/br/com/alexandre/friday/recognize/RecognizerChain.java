package br.com.alexandre.friday.recognize;

import java.util.Collection;

public abstract class RecognizerChain {
    private RecognizerChain next;

    public Collection<String> handle(final RecognizerRequest request) {
        if (apply(request)) {
            return execute(request);
        } else {
            return this.next.handle(request);
        }
    }

    public abstract Collection<String> execute(final RecognizerRequest request);

    public abstract boolean apply(final RecognizerRequest request);

    public void setNext(final RecognizerChain next) {
        this.next = next;
    }
}