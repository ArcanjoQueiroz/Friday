package br.com.alexandre.friday.recognize.chain;

import br.com.alexandre.friday.recognize.RecognizerChain;
import br.com.alexandre.friday.recognize.RecognizerRequest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

public class GoodbyeChain extends RecognizerChain {

    public Collection<String> execute(RecognizerRequest request) {
        return Arrays.asList(String.format("Adeus, %s! Que você tenha uma ótima %s!", request.getName(), partOfTheDay()));
    }

    public boolean apply(RecognizerRequest request) {
        return (request.getQ().contains("adeus") || request.getQ().contains("tchau"));
    }

    private String partOfTheDay() {
        switch (PartOfTheDay.get(Calendar.getInstance())) {
            case MORNING:
                return "manhã";
            case AFTERNOON:
                return "tarde";
            default:
                return "noite";
        }
    }

    public enum PartOfTheDay {
        MORNING,
        AFTERNOON,
        EVENING,
        NIGHT;

        public static PartOfTheDay get(final Calendar now) {
            final int hours = now.get(Calendar.HOUR_OF_DAY);
            final int minutes = now.get(Calendar.MINUTE);
            if (hours >= 5 && hours < 12) {
                return PartOfTheDay.MORNING;
            } else if (hours >= 12 && hours < 17) {
                return PartOfTheDay.AFTERNOON;
            } else if (hours >= 17 && hours < 21) {
                return PartOfTheDay.EVENING;
            }
            return PartOfTheDay.NIGHT;
        }
    }
}
