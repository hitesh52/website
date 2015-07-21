package com.localexample;


public class ListItem_kw_two_two_name {
    String twokw_name;
    String twokw_title;
    String twokw_desc;
    String twokw_content;
    String twofreq;
    public ListItem_kw_two_two_name(String twokw_name,String twokw_title,String twokw_desc,String twokw_content,String twofreq){
        super();
        this.twokw_name = twokw_name;
        this.twokw_title=twokw_title;
        this.twokw_desc=twokw_desc;
        this.twokw_content=twokw_content;
        this.twofreq=twofreq;
    }
    public String getKw_twoname(){
        return twokw_name;
    }
    public void setKw_twoname(String twokw_name)
    {
        this.twokw_name = twokw_name;
    }

    public String getKw_twotitle(){
        return twokw_title;
    }
    public void setKw_twotitle(String twokw_title)
    {
        this.twokw_title = twokw_title;
    }
    public String getKw_twodesc(){
        return twokw_desc;
    }
    public void setKw_twodesc(String twokw_desc)
    {
        this.twokw_desc = twokw_desc;
    }
    public String getKw_twocontent(){
        return twokw_content;
    }
    public void setKw_twocontent(String twokw_content)
    {
        this.twokw_content= twokw_content;
    }
    public String gettwoFreq(){
        return twofreq;
    }
    public void settwoFreq(String twofreq)
    {
        this.twofreq= twofreq;
    }



}
