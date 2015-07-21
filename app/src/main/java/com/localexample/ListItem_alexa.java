package com.localexample;

public class ListItem_alexa {
    String country_name;
    String country_value;

    public ListItem_alexa(String country_name, String country_value){
        super();
        this.country_name=country_name;
        this.country_value=country_value;


    }
    public String getCountry_name(){
        return country_name;
    }
    public void setCountry_name(String country_name)
    {
        this.country_name = country_name;
    }
    public void setCountry_value(String country_value){
        this.country_value= country_value;
    }



    public String getCountry_value(){
        return country_value;
    }

}
