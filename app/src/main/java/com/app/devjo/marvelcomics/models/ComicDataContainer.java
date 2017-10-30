package com.app.devjo.marvelcomics.models;

import java.util.List;

/**
 * Created by kjoha on 23/3/2017.
 *
 *Layer level 2 call api rest comics
 */

public class ComicDataContainer {

    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<Comic> results;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public List<Comic> getResults() {
        return results;
    }
}
