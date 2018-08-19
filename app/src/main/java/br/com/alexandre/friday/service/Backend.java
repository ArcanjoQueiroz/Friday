package br.com.alexandre.friday.service;

import java.util.Collection;

import br.com.alexandre.friday.task.Answer;

public interface Backend {
    public Collection<String> ask(final String q, final Answer.Context context);

    public static class OfflineException extends RuntimeException {
        public OfflineException(final Throwable cause) {
            super(cause);
        }
    }
}
