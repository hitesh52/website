package com.localexample;

public class ListItem_speed_tips {
    String message;
    String score;
    String value;
    String data;
    public ListItem_speed_tips(String message, String score,String value,String data){
        super();
        this.message = message;
        this.score = score;
        this.value=value;
        this.data=data;

    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public void setScore(String score){
        this.score = score;
    }
    public String getScore(){
        return score;
    }
    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    public String getData(){
        return data;
    }
    public void setData(String data)
    {
        this.data = data;
    }


}
