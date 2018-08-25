package br.com.alexandre.friday.recognize;


import java.io.Serializable;

public class RecognizerRequest implements Serializable {
    private String q;
    private String name;
    private Double latitude;
    private Double longitude;
    private String countryName;
    private String locality;
    private String subLocality;
    private String thoroughfare;
    private String subThoroughfare;

    public RecognizerRequest() {}

    public RecognizerRequest(String q, String name) {
        this.q = q;
        this.name = name;
    }

    public RecognizerRequest(String q, String name, Double latitude, Double longitude) {
        this.q = q;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getQ() {
        return (q != null)? q.toLowerCase(): null;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getSubLocality() {
        return subLocality;
    }

    public void setSubLocality(String subLocality) {
        this.subLocality = subLocality;
    }

    public String getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(String thoroughfare) {
        this.thoroughfare = thoroughfare;
    }

    public String getSubThoroughfare() {
        return subThoroughfare;
    }

    public void setSubThoroughfare(String subThoroughfare) {
        this.subThoroughfare = subThoroughfare;
    }
}