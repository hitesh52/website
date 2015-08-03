package com.localexample;

public class ListItem_speed {
    String request;
    String speed;
    String obj_name;
    public ListItem_speed(String request, String speed,String obj_name){
        super();
        this.request = request;
        this.speed = speed;
        this.obj_name=obj_name;


    }
    public String getRequest(){
        return request;
    }
    public void setSpeed(String speed)
    {
        this.speed = speed;
    }
    public void setRequest(String request){
        this.request = request;
    }
    public String getObj_name(){
        return obj_name;
    }
    public void setObj_name(String obj_name){
        this.obj_name = obj_name;
    }

    public String getSpeed(){
        return speed;
    }


}
