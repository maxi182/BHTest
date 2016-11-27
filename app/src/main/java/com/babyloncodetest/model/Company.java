package com.babyloncodetest.model;

/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */

public class Company {

    private final String catchPhrase;
    private final String name;
    private final String bs;

    public Company(String catchPhrase, String name, String bs) {
        this.catchPhrase = catchPhrase;
        this.name = name;
        this.bs = bs;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getName() {
        return name;
    }

    public String getBs() {
        return bs;
    }
}

