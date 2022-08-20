package service.impl;

import dao.FishDAO;
import dao.UserDao;
import dao.impl.FishDAOImpl;
import dao.impl.UserDaoImpl;
import domain.Fish;
import service.FishingSeivice;

import java.util.Random;

public class FishingServiceImpl implements FishingSeivice {
    UserDao userDao = new UserDaoImpl();
    @Override
    public String luckyFishing(int userId) {
        Random r = new Random();
        int x = r.nextInt(1000);
        if (x <= 20) {
            //极品鱼或道具
            //1种
            //Dao层方法查找某种鱼/道具
            //添加到背包
            return "SSR";
        } else if (x > 20 && x <= 200) {
            //稀有鱼或道具
            //3种（20-80 80-140 140-200）
            return "SR";
        } else {
            //普通鱼或道具
            //6种
            return "R";
        }
    }

    @Override
    public void reducePoints(int userId) {
        userDao.reducePoint(200, userId);
    }
}
