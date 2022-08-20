package service.impl;

import domain.Item;
import domain.User;
import service.MaketService;

import java.util.List;

public class MaketServiceImpl implements MaketService {



    @Override
    public List<Item> showMaket() {
        //商品1  价格100  商品名
        //mysql的人去查数据库
        return null;
    }

    @Override
    public int shopping(Item item, User user) {
        if (user.getPoints() > item.getPrice()){
            //购买成功
            //addItems
        }
        return 0;
    }

}