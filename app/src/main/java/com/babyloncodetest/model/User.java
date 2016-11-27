package com.babyloncodetest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */

public class User {

    @SerializedName("id")
    public int userid;
    public String email;
    public Address address;
    public Company company;
    public String phone;
    public String website;


}
