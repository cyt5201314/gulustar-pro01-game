package domain;

import java.sql.Date;
import java.sql.Time;

/**
 * 鱼表
 */
public class Fish{
    private int id;
    private int type;//鱼种类
    private String name;  //鱼名
    private int price;//价格
    private int userId;//用户ID
    private Date startTime;//开始养的时间
    private int exp;//经验
    private int sell;//售价
    private Time matureTime;//成熟时间
    private int isMature;//0未成熟 1成熟

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public Time getMatureTime() {
        return matureTime;
    }

    public void setMatureTime(Time matureTime) {
        this.matureTime = matureTime;
    }

    public int getIsMature() {
        return isMature;
    }

    public void setIsMature(int isMature) {
        this.isMature = isMature;
    }
}
