package com.localexample;

public class ListItem_spell {
    String wrong_word;
    String substitute;
    String info;
    public ListItem_spell(String wrong_word, String substitute, String info){
        super();
        this.wrong_word = wrong_word;
        this.substitute = substitute;
        this.info = info;

    }
    public String getWrong_word(){
        return wrong_word;
    }
    public void setWrong_word(String wrong_word)
    {
        this.wrong_word = wrong_word;
    }
    public void setSubstitute(String substitute){
        this.substitute = substitute;
    }
    public void setInfo(String info){
        this.info = info;
    }

    public String getSubstitute(){
        return substitute;
    }
    public String getInfo(){
        return info;
    }

}
