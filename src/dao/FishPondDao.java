package dao;

import domain.FishPond;

public interface FishPondDao {
    /**
     * 获取用户鱼塘容量
     */
    int getCapacity(int userId);
}
