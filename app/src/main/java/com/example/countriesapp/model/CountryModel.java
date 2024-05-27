package com.example.countriesapp.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {

    @SerializedName("name")
    String countryName;
    @SerializedName("capital")
    String capital;
    @SerializedName("flagPNG")
    String flag;

    public CountryModel(String countryName, String capital, String flag) {
        this.countryName = countryName;
        this.capital = capital;
        this.flag = flag;
    }
    public String getCountryName() {
        return countryName;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
