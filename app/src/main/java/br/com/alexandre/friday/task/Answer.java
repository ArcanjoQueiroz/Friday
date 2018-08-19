package br.com.alexandre.friday.task;

import android.app.Application;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import br.com.alexandre.friday.service.Backend;

public class Answer extends AsyncTask<String, Void, Collection<String>> {
    private Backend backend;
    private Answer.Context context;

    public Answer(final Backend backend, final Answer.Context context) {
        this.backend = backend;
        this.context = context;
    }

    @Override
    protected Collection<String> doInBackground(final String... strings) {
        Collection<String> response = null;
        try {
            response = this.backend.ask(strings[0], context);
        } catch (final Backend.OfflineException e) {
            response = Arrays.asList("Parece que a informação está offline no momento. Tente novamente mais tarde.");
        } catch (final RuntimeException e) {
            response = Arrays.asList("Ocorreu um erro ao buscar a informação.");
            Log.e("Error", "There is an error on answer: " + e.getMessage(), e);
        }
        return response;
    }

    public static class Context implements Serializable {
        private String name;
        private Application application;
        private Location location;

        public Context(final String name, final Application application, final Location location) {
            this.name = name;
            this.application = application;
            this.location = location;
        }

        public Location getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public Application getApplication() {
            return application;
        }
    }

}
