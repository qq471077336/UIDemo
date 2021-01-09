package com.lwd.uidemo.cardslide;

import java.util.ArrayList;
import java.util.List;

public class CardBean {
    private int position;
    private String url;
    private String name;

    public CardBean(int position, String url, String name) {
        this.position = position;
        this.url = url;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public CardBean setPosition(int position) {
        this.position = position;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CardBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardBean setName(String name) {
        this.name = name;
        return this;
    }

    public static List<CardBean> initDatas() {
        List<CardBean> datas = new ArrayList<>();
        int i = 1;
        datas.add(new CardBean(i++, "https://game.gtimg.cn/images/lol/act/img/skin/big4013.jpg", "卡牌"));
        datas.add(new CardBean(i++, "https://game.gtimg.cn/images/lol/act/img/skin/big15010.jpg", "轮子妈"));
        datas.add(new CardBean(i++, "https://game.gtimg.cn/images/lol/act/img/skin/big18000.jpg", "小炮"));
        datas.add(new CardBean(i++, "https://game.gtimg.cn/images/lol/act/img/skin/big157036.jpg", "亚索"));
        datas.add(new CardBean(i++, "https://game.gtimg.cn/images/lol/act/img/skin/big92022.jpg", "锐雯"));
        return datas;
    }
}
