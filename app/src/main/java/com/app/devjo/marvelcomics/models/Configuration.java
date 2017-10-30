package com.app.devjo.marvelcomics.models;

/**
 * Created by kjohan182 on 21/3/2017.
 */

public class Configuration {

    public static final String publicKey = "2359abf629c50fbfd70cf59e778dabed";
    public static final String ts = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String hash = "2dd92d5450d3e0daedb1d90abb61fcc8";
    public static final String API_BASE_URL = "https://gateway.marvel.com:443/";
    public static final int LIMIT = 30;
    public static final String title= "title";

    //opc case
    public static final String COMICS_LIST_DEFAULT = "default";
    public static final String COMICS_LIST_FIND_TITLE = "findTitle";
    public static final String COMICS_LIST_ORDER_BY = "orderBY";


    public static String getPublicKey() {
        return publicKey;
    }

    public static String getTs() {
        return ts;
    }

    public static String getHash() {
        return hash;
    }

    public static String getApiBaseUrl() {
        return API_BASE_URL;
    }

    public static int getLIMIT() {
        return LIMIT;
    }

    public static String getTitle() {
        return title;
    }
}
