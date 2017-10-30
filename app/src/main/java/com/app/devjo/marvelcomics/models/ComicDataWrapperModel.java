package com.app.devjo.marvelcomics.models;

import java.util.List;

/**
 * Created by kjoha on 23/3/2017.
 *
 * Layer level 1 call api rest comics
 */

public class ComicDataWrapperModel {

    public int code;
    public String status;
    public ComicDataContainer data;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public ComicDataContainer getData() {
        return data;
    }
}
