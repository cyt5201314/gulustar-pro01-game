package service.impl;

import domain.Item;
import domain.User;
import service.ItemService;

public class ItemServiceImpl implements ItemService {
        @Override
    public int addItems(User user, Item item) {
            //添加到数据库
            return 0;
    }

    @Override
    public int deleteItems(User user, Item item) {
        return 0;
    }
}
