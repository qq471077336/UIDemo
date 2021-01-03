package com.lwd.uidemo.stickyheader;

/**
 * @AUTHOR lianwd
 * @TIME 1/3/21
 * @DESCRIPTION 英雄
 */
public class Hero {
    /** 英雄名 */
    private String name;
    /** 英雄所属国家 */
    private String country;

    public Hero(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
