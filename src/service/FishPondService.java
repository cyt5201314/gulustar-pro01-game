package service;

import domain.FishPond;
import domain.User;

import java.util.List;

public interface FishPondService {
    /**
     * 扩建鱼塘的方法
     * @param fishPond
     * @param user
     * @return 0表示失败，1表示成功
     */
     int addFishPond(FishPondService fishPond, User user);

    /**
     * 获取指定用户的池塘容量
     * @param userId
     * @return
     */
     int getCapacity(int userId);
}
