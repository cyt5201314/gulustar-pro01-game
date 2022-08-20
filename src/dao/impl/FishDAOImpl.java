package dao.impl;

import dao.FishDAO;
import domain.Fish;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.sql.Time;
import java.util.List;

public class FishDAOImpl implements FishDAO {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Fish> getFishPondById(int id) {
        try {
            String sql = "select * from fishes where userId = ? and startTime is not null";
            List<Fish> fishes = template.query(sql, new BeanPropertyRowMapper<Fish>(Fish.class), id);
            return fishes;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Fish> getBagFishById(int id) {
        try {
            String sql = "select * from fishes where userId = ? and startTime is null";
            List<Fish> fishes = template.query(sql, new BeanPropertyRowMapper<Fish>(Fish.class), id);
            return fishes;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Fish> getShopFish() {
        try {
            String sql = "select * from fishes where userId = 0";
            return template.query(sql, new BeanPropertyRowMapper<Fish>(Fish.class));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addFish(int type, int userId) {
        Fish fish = getFishByType(type);
        String sql = "insert into fishes values(0,?,?,?,?,?,?,?,?,?)";
        return template.update(sql,fish.getType(),fish.getName(),fish.getPrice(),userId,fish.getStartTime(),fish.getExp(),fish.getSell(),fish.getMatureTime(),fish.getIsMature());
    }

    @Override
    public Fish getFishByType(int type) {
        String sql = "select * from fishes where type = ? and userId = 0";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<Fish>(Fish.class),type);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateTime(int fishId) {
        String sql = "update fishes set startTime = NOW() where id = ?";
        try {
            return template.update(sql,fishId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String getRemainMatureTime(int fishId) {
        String sql = "SELECT GREATEST(TIMEDIFF(matureTime,TIMEDIFF(NOW(),startTime)),0) FROM fishes WHERE id = ?";
        String time = null;
        try {
            time = template.queryForObject(sql,String.class, fishId);
            return time;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int setFishMature(int fishId) {
        String sql = "update fishes set isMature = 1 where id = ?";
        try {
            return template.update(sql,fishId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteFish(int fishId) {
        String sql = "delete from fishes where id = ?";
        return template.update(sql,fishId);
    }
}
