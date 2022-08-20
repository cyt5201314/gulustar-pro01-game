package service.impl;

import dao.FishDAO;
import dao.UserDao;
import dao.impl.FishDAOImpl;
import dao.impl.UserDaoImpl;
import domain.Bag;
import domain.Fish;
import domain.FishPond;
import domain.User;
import service.FishPondService;
import service.FishService;

import java.sql.Time;
import java.util.List;

public class FishServiceImpl implements FishService {

    FishDAO fishDAO = new FishDAOImpl();
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<Fish> getFishPond(int userId) {
        return fishDAO.getFishPondById(userId);
    }

    @Override
    public List<Fish> getBagFish(int userId) {
        return fishDAO.getBagFishById(userId);
    }

    @Override
    public List<Fish> getOnSaleFish() {
        return fishDAO.getShopFish();
    }

    @Override
    public int buyFish(int type, int userId) {
        Fish fish = fishDAO.getFishByType(type);
        User user = userDao.getUserById(userId);
        //如果买的起  就扣积分 然后添加给用户
        if (user.getPoints() >= fish.getPrice()) {
            userDao.reducePoint(fish.getPrice(), userId);
            return fishDAO.addFish(type, userId);
        } else {
            return 0;
        }
    }

    @Override
    public long checkFish(Fish fish) {
        return 0;
    }

    @Override
    public int fishHarvesting(Fish fish) {
        //删除鱼
        fishDAO.deleteFish(fish.getId());
        //增加积分
        userDao.addPoint(fish.getSell(), fish.getUserId());
        //增加经验
        userDao.addExp(fish.getExp(), fish.getUserId());
        //计算等级
        User user = userDao.getUserById(fish.getUserId());
        userDao.updateLevel(fish.getUserId(),user.getCurexp());
        return 1;
    }

    @Override
    public int fishHarvesting(Fish fish, int visitorId) {
        //删除鱼
        fishDAO.deleteFish(fish.getId());
        //增加积分
        userDao.addPoint(fish.getSell(), visitorId);
        //增加经验
        userDao.addExp(fish.getExp(), visitorId);
        //计算等级
        User user = userDao.getUserById(visitorId);
        userDao.updateLevel(visitorId,user.getCurexp());
        return 1;
    }

    @Override
    public int startFeeding(int fishId, int userId) {
        FishPondService fishPondService = new FishPondServiceImpl();
        int capacity = fishPondService.getCapacity(userId);
        if (capacity > fishDAO.getFishPondById(userId).size()) {
            return fishDAO.updateTime(fishId);
        } else {
            return 0;
        }
    }

    @Override
    public String getRemainMatureTime(int fishId) {

        return fishDAO.getRemainMatureTime(fishId);
    }

    @Override
    public int setMature(int fishId) {
        return fishDAO.setFishMature(fishId);
    }
}
