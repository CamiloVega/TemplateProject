package com.cvdevelopers.restfull.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

/**
 * Created by CamiloVega on 2/20/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("user")
public class User extends GUIDModel {

    public static final String NAME_FIELD = "first_name";
    public static final String LAST_NAME_FIELD = "last_name";
    public static final String PICTURE_FIELD = "picture";
    public static final String CEDULA_FIELD = "cedula";
    public static final String PHONE_NUMBER_FIELD = "phone_number";
    public static final String ADDRESS_FIELD = "address";
    public static final String EMAIL_FIELD = "email";
    public static final String PASSWORD_FIELD = "password";
    public static final String BANK_FIELD = "bank";
    public static final String VERIFIED_USER_FIELD = "verified_user";
    public static final String FACEBOOK_CONNECTED_FIELD = "facebook_connected";
    public static final String CITY_FIELD = "city";
    public static final String COUNTRY_FIELD = "country";
    public static final String VERIFIED_PHONE_NUMBER_FIELD = "verified_phone_number";
    public static final String REGISTRATION_DATE_FIELD = "registration_date";
    public static final String STATS_FIELD = "stats";

    @JsonProperty(NAME_FIELD)
    private String name;

    @JsonProperty(LAST_NAME_FIELD)
    private String lastName;

    @JsonProperty(PICTURE_FIELD)
    private String picture;

    @JsonProperty(CEDULA_FIELD)
    private String cedula;

    @JsonProperty(PHONE_NUMBER_FIELD)
    private String phoneNumber;

    @JsonProperty(ADDRESS_FIELD)
    private String address;

    @JsonProperty(EMAIL_FIELD)
    private String email;

//    @JsonProperty(BANK_FIELD)
//    private String email;

    @JsonProperty(VERIFIED_USER_FIELD)
    private Integer _verifiedUser;

    @JsonProperty(FACEBOOK_CONNECTED_FIELD)
    private Integer _facebookConnected;

    @JsonProperty(CITY_FIELD)
    private String city;

    @JsonProperty(COUNTRY_FIELD)
    private String country;

    @JsonProperty(VERIFIED_PHONE_NUMBER_FIELD)
    private Integer _verifiedPhoneNumber;

    @JsonProperty(REGISTRATION_DATE_FIELD)
    private Date registrationDate;

    @JsonProperty(STATS_FIELD)
    private String stats;

    @JsonProperty(PASSWORD_FIELD)
    private String password;

    public User() {
    }

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer get_verifiedUser() {
        return _verifiedUser;
    }

    public void set_verifiedUser(Integer _verifiedUser) {
        this._verifiedUser = _verifiedUser;
    }

    public Integer get_facebookConnected() {
        return _facebookConnected;
    }

    public void set_facebookConnected(Integer _facebookConnected) {
        this._facebookConnected = _facebookConnected;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer get_verifiedPhoneNumber() {
        return _verifiedPhoneNumber;
    }

    public void set_verifiedPhoneNumber(Integer _verifiedPhoneNumber) {
        this._verifiedPhoneNumber = _verifiedPhoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
