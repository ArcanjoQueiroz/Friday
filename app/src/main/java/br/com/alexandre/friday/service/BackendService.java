package br.com.alexandre.friday.service;

import android.app.Application;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.alexandre.friday.recognize.RecognizerHandler;
import br.com.alexandre.friday.recognize.RecognizerRequest;
import br.com.alexandre.friday.recognize.chain.AddressChain;
import br.com.alexandre.friday.recognize.chain.CityChain;
import br.com.alexandre.friday.recognize.chain.DontMakeMistakesChain;
import br.com.alexandre.friday.recognize.chain.GoodbyeChain;
import br.com.alexandre.friday.recognize.chain.HelloChain;
import br.com.alexandre.friday.recognize.chain.LocationChain;
import br.com.alexandre.friday.recognize.chain.NeighborhoodChain;
import br.com.alexandre.friday.recognize.chain.StreetChain;
import br.com.alexandre.friday.recognize.chain.SuccessfulMessageSentChain;
import br.com.alexandre.friday.recognize.chain.ThankYouChain;
import br.com.alexandre.friday.task.Answer;

public class BackendService implements Backend {

    private RecognizerHandler handler = new RecognizerHandler(
            Arrays.asList(
                new AddressChain(),
                new CityChain(),
                new DontMakeMistakesChain(),
                new GoodbyeChain(),
                new HelloChain(),
                new LocationChain(),
                new NeighborhoodChain(),
                new StreetChain(),
                new SuccessfulMessageSentChain(),
                new ThankYouChain()
        ));

    public BackendService() { }

    @Override
    public Collection<String> ask(final String q, final Answer.Context context) {
        final RecognizerRequest request = new RecognizerRequest();
        request.setQ(q);
        request.setName(context.getName());
        if (context.getLocation() != null) {
            request.setLatitude(context.getLocation().getLatitude());
            request.setLongitude(context.getLocation().getLongitude());

            final Address address = getAddress(context.getApplication(), context.getLocation().getLatitude(), context.getLocation().getLongitude());
            if (address != null) {
                request.setCountryName(address.getCountryName());
                request.setLocality(address.getLocality());
                request.setSubLocality(address.getSubLocality());
                request.setThoroughfare(address.getThoroughfare());
                request.setSubThoroughfare(address.getSubThoroughfare());
            }
        }
        final Collection<String> response = handler.handle(request);
        Log.i("Response", response.toString());
        return response;
    }

    private Address getAddress(final Application application, final Double latitude, final Double longitude) {
        final List<Address> fromLocation;
        try {
            fromLocation = new Geocoder(application.getBaseContext()).getFromLocation(latitude, longitude, 1);
            return (fromLocation != null && !fromLocation.isEmpty())? fromLocation.get(0): null;
        } catch (IOException e) {
            Log.e("Error", "There is an error on getAddress from latitude and longitude", e);
        }
        return null;
    }
}
