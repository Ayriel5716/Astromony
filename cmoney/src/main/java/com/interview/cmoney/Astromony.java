package com.interview.cmoney;

public class Astromony {
    String description;
    String copyright;
    String title;
    String url;
    String date;
    String hdurl;

    public Astromony(String description, String copyright, String title, String url, String date, String hdurl) {
        this.description = description;
        this.copyright = copyright;
        this.title = title;
        this.url = url;
        this.date = date;
        this.hdurl = hdurl;
    }

    public Astromony() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String[] dateFormat = date.split("-");
                    switch (dateFormat[1]) {
                        case "1":
                            dateFormat[1] = "Jan.";
                            break;
                        case "2":
                            dateFormat[1] = "Feb.";
                            break;
                        case "3":
                            dateFormat[1] = "Mar.";
                            break;
                        case "4":
                            dateFormat[1] = "Apr.";
                            break;
                        case "5":
                            dateFormat[1] = "May.";
                            break;
                        case "6":
                            dateFormat[1] = "Jun.";
                            break;
                        case "7":
                            dateFormat[1] = "Jul.";
                            break;
                        case "8":
                            dateFormat[1] = "Aug.";
                            break;
                        case "9":
                            dateFormat[1] = "Sep.";
                            break;
                        case "10":
                            dateFormat[1] = "Oct.";
                            break;
                        case "11":
                            dateFormat[1] = "Nov.";
                            break;
                        case "12":
                            dateFormat[1] = "Dec.";
                            break;
                    }
                     date = dateFormat[0]+" "+dateFormat[1]+" "+dateFormat[2];
                    this.date = date;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }
}
