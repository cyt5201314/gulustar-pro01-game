package service;

import domain.Bag;
import domain.Fish;
import domain.FishPond;
import domain.User;

import java.sql.Time;
import java.util.List;

public interface FishService {

    /**
     * 获得用户池塘里所有的鱼
     * @param userId
     */
    List<Fish> getFishPond(int userId);

    /**
     * 获得用户背包里的鱼
     * @param userId
     * @return
     */
    List<Fish> getBagFish(int userId);

    /**
     * 获取商城的鱼
     * @return
     */
    List<Fish> getOnSaleFish();

    /**
     *    商城购买操作
     * @param type
     * @return
     */
    int buyFish(int type,int userId);

    /**
     * 查看鱼还有多久长大
     * @param fish,外键绑定用户
     */
     long checkFish(Fish fish);

    /**
     *
     * 用户收鱼的方法，返回卖鱼所获得的积分
     * @param fish
     */
     int fishHarvesting(Fish fish);

    /**
     * 偷鱼
     * @param fish
     * @param visitorId
     * @return
     */
     int fishHarvesting(Fish fish,int visitorId);

    /**
     * 开始培养指定鱼
     * @param fishId
     * @return
     */
     int startFeeding(int fishId,int userId);

    /**
     * 获取剩余成熟时间
     * @param fishId
     * @return
     */
     String getRemainMatureTime(int fishId);

    /**
     * 设置鱼成熟
     * @return
     */
    int setMature(int fishId);

}

