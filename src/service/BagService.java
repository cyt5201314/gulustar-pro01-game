package service;

import domain.Bag;
import domain.Item;
import domain.User;

import java.util.List;

/**
 * 背包管理的业务接口
 */
public interface BagService {
    /**
     * 查看背包
     * @param user
     * @return
     */
    List<Bag> checkBag(User user);

    /**
     * 往背包里添加道具的方法
     * @param user
     * @param item
     */
    void addBag(User user, Item item);

    /**
     * 往背包里移除道具的方法
     * @param user
     * @param item
     */
    void deleteBag(User user, Item item);

}
