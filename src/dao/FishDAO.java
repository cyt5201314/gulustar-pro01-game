package dao;

import domain.Fish;

import java.sql.Time;
import java.util.List;

public interface FishDAO {

    /**
     * 获得指定用户鱼塘里所有的鱼
     * @param id
     * @return
     */
    List<Fish> getFishPondById(int id);

    /**
     * 查找指定用户背包里的鱼苗
     * @param id
     * @return
     */
    List<Fish> getBagFishById(int id);

    /**
     * 获取商城出售的鱼苗
     * @return
     */
    List<Fish> getShopFish();

    /**
     * 添加鱼   商城购买操作
     * @param type
     * @param userId
     * @return
     */
    int addFish(int type,int userId);

    /**
     * 获取指定类型的鱼
     * @param type
     * @return
     */
    Fish getFishByType(int type);

    /**
     * 更新鱼的开始时间  培养操作
     * @param fishId
     * @return
     */
    int updateTime(int fishId);

    /**
     * 获取指定鱼的剩余成熟时间
     * @param fishId
     * @return
     */
    String getRemainMatureTime(int fishId);

    /**
     * 把鱼设置为成熟
     * @param fishId
     * @return
     */
    int setFishMature(int fishId);

    /**
     * 删除鱼的记录
     * @param fishId
     * @return
     */
    int deleteFish(int fishId);
}
