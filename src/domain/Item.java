package domain;

/**
 * 物品类
 */
public class Item {
    private int id;
    private int type;//种类
    private String itemname;
    private int price;
    private int userid; //0表示商城商品，不为0表示为具体用户道具

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

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    //道具ID   。。 。。。 。。  用户ID
    // 1     。。。。。。。。。。  1
    // 1     ...............   1
    // 3       ..............  1
    // 4       ..............  1
    // 4   ..................  1
    // 4   ..................  1
    // 4   ..................  1
    // 1   ..................  0
}
