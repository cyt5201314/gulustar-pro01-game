package service;


import domain.Bag;
import domain.Item;
import domain.User;

public interface ItemService {

    /**
     * 添加道具
     * @return
     */
    int addItems(User user, Item item);

    /**
     * 删除道具
     * @param user
     * @param item
     * @return
     */
    int deleteItems(User user,Item item);


}
