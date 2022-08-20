package service;

import domain.Bag;
import domain.Item;
import domain.User;

import java.util.List;

public interface MaketService {
    /**
     * 用户查看商城
     */
    List<Item> showMaket();

    /**
     * 用户购物的方法
     * @param item
     * @param user
     * @return 0表示失败,1表示成功
     */
    int shopping(Item item, User user);
}
