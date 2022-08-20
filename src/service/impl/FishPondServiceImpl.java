package service.impl;

import dao.FishPondDao;
import dao.impl.FishPondDaoImpl;
import domain.FishPond;
import domain.User;
import service.FishPondService;

import java.util.List;

public class FishPondServiceImpl implements FishPondService{

    FishPondDao fishPondDao = new FishPondDaoImpl();

    @Override
    public int addFishPond(FishPondService fishPond, User user) {
        return 0;
    }

    @Override
    public int getCapacity(int userId) {
        return fishPondDao.getCapacity(userId);
    }


}
