package br.com.alexandre.friday.recognize;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RecognizerHandler {

    private RecognizerChain chain;
    private RecognizerChain errorChain;

    public RecognizerHandler(final List<? extends RecognizerChain> elements) {
        this.chain = createChainFromInstances(elements);
    }

    private RecognizerChain createChainFromInstances(List<? extends RecognizerChain> elements) {
        final List<RecognizerChain> instances = new ArrayList<RecognizerChain>();
        for (final RecognizerChain element: elements) {
            try {
                if (!element.getClass().isInstance(DefaultChain.class) && !element.getClass().isAssignableFrom(DefaultChain.class)) {
                    instances.add(element);
                }
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
        instances.add((getErrorChain() != null)? getErrorChain(): getDefault());
        return configureChain(instances);
    }

    public RecognizerChain configureChain(final List<? extends RecognizerChain> instances) {
        for (int i = 0; i < instances.size()-1; i++) {
            instances.get(i).setNext(instances.get(i+1));
        }
        return instances.get(0);
    }

    public Collection<String> handle(final RecognizerRequest request) {
        return this.chain.handle(request);
    }

    public RecognizerChain getErrorChain() {
        return errorChain;
    }

    public void setErrorChain(RecognizerChain errorChain) {
        this.errorChain = errorChain;
    }

    private RecognizerChain getDefault() {
        return new DefaultChain();
    }

    static class DefaultChain extends RecognizerChain {
        @Override
        public Collection<String> execute(RecognizerRequest request) {
            return Arrays.asList("Não reconheci o seu comando. Fale mais pausadamente, por favor");
        }

        @Override
        public boolean apply(RecognizerRequest request) {
            return true;
        }
    };
}