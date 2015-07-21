package com.localexample;


public class ListItem {
    String url;
    String counter;
    String robo;
    String site;
    String title;
    String meta;
    public ListItem(String url,String counter){
        super();
        this.url = url;
        this.counter = counter;
        this.robo = robo;
        this.site = site;
        this.title = title;

        this.meta = meta;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public void setCounter(String counter){
        this.counter = counter;
    }
    public void setRobo(String robo){
        this.robo = robo;
    }
    public void setSite(String site){
        this.site = site;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setMeta(String meta){
        this.meta = meta;
    }
    public String getCounter(){
        return counter;
    }
    public String getRobo(){
        return robo;
    }
    public String getSite(){
        return site;
    }
    public String getTitle(){
        return title;
    }
    public String getMeta(){
        return meta;
    }
}
