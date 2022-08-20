package domain;

import java.util.Date;
import java.util.List;

/**
 * 鱼塘类
 */
public class FishPond {
    private int capacity;//鱼塘容量
    private int userId;//哪个用户的

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
