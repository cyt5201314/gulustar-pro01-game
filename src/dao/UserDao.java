package dao;
import domain.User;

import java.util.List;

/**
 *
 * 用户操作的Dao
 */
public interface UserDao {

    /**
     * 根据提交的用户名和密码找到对应的数据并且直接封装成user对象
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 用户注册的方法
     * @param user
     */
    void add(User user);

    /**
     * 根据用户名查找user
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 获取用户积分
     * @param userId
     * @return
     */
    User getUserById(int userId);

    /**
     * 减少用户积分
     * @param userId
     */
    void reducePoint(int amount,int userId);

    /**
     * 增加用户积分
     * @param amount
     * @param userId
     */
    void addPoint(int amount,int userId);

    /**
     * 增加用户经验
     * @param amount
     * @param userId
     */
    void addExp(int amount,int userId);

    /**
     * 给用户初始鱼塘
     * @param user
     */
    void addFishPond(User user);

    /**
     * 更新等级
     * @param userId
     * @param exp
     */
    void updateLevel(int userId,int exp);

    List<User> rank();
}
