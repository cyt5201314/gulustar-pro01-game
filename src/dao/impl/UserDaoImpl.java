package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class UserDaoImpl implements UserDao{
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {

        try {
            //1.定义sql
            String sql = "select * from user where username = ? and password = ?";
            User user = null;
            try {
                user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            } catch (DataAccessException e) {
                return null;
            }
            return user;
        } catch (Exception e) {
            //没查到东西会报错，抓个异常
            return null;
        }
    }

    public void addFishPond(User user){
        //1.定义sql
        String sql = "insert into fishpond values(5,?)";
        //2.执行sql
        template.update(sql,user.getId());
    }

    @Override
    public void updateLevel(int userId, int exp) {
        String sql = "UPDATE USER SET LEVEL = (SELECT MAX(grade) FROM grade WHERE EXP <= ?) WHERE id = ?";
        template.update(sql,exp,userId);
    }

    @Override
    public void add(User user) {
        //1.定义sql
        String sql = "insert into user values(null,?,?,0,0,1)";
        //2.执行sql
        template.update(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select * from user where username = ?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        } catch (DataAccessException e) {
            //没查到东西会报错，抓个异常
            return null;
        }
    }

    @Override
    public User getUserById(int userId) {
        String sql = "select * from user where id = ?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void reducePoint(int amount,int userId) {
        String sql = "update user set points = points - ? where id = ?";
        template.update(sql,amount,userId);
    }

    @Override
    public void addPoint(int amount,int userId) {
        String sql = "update user set points = points + ? where id = ?";
        template.update(sql,amount,userId);
    }

    @Override
    public void addExp(int amount, int userId) {
        String sql = "update user set curExp = curExp + ? where id = ?";
        template.update(sql,amount,userId);
    }

    @Override
    public List<User> rank() {
        String sql = "select * from user order by level desc";
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }

}
