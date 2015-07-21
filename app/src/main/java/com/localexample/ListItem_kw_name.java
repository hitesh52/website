package com.localexample;


public class ListItem_kw_name {
    String kw_name;
    String kw_title;
    public ListItem_kw_name(String kw_name,String kw_title){
        super();
        this.kw_name = kw_name;
        this.kw_title=kw_title;

    }
    public String getKw_name(){
        return kw_name;
    }
    public void setKw_name(String kw_name)
    {
        this.kw_name = kw_name;
    }

    public String getKw_title(){
        return kw_title;
    }
    public void setKw_title(String kw_title)
    {
        this.kw_title = kw_title;
    }


}
