package com.localexample;


public class ListItem_kw_two_name {
    String kw_name;
    String kw_title;
    String kw_desc;
    String kw_content;
    String freq;
    public ListItem_kw_two_name(String kw_name,String kw_title,String kw_desc,String kw_content,String freq){
        super();
        this.kw_name = kw_name;
        this.kw_title=kw_title;
        this.kw_desc=kw_desc;
        this.kw_content=kw_content;
        this.freq=freq;
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
    public String getKw_desc(){
        return kw_desc;
    }
    public void setKw_desc(String kw_desc)
    {
        this.kw_desc = kw_desc;
    }
    public String getKw_content(){
        return kw_content;
    }
    public void setKw_content(String kw_content)
    {
        this.kw_content= kw_content;
    }
    public String getFreq(){
        return freq;
    }
    public void setFreq(String freq)
    {
        this.freq= freq;
    }



}
