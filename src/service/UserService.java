package service;

import domain.*;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 用户注册的方法
     * @param username
     * @param password
     */
     String registe(String username,String password);

    /**
     * 用户登陆的方法
     * @param username
     * @param password
     * @return 成功返回user对象，失败返回null，提示登陆失败
     */
     User login(String username,String password);


    /**
     * 用户签到的功能
     */
     void singIn(User user);

    /**
     * 展示排行榜的方法
     *
     * @return 返回排序后的集合，展示到前台上
     */
     List<User> rank();

    /**
     * 根据ID查找用户
     * @param userId
     * @return
     */
     User getUserById(int userId);

     void addexp(int id,Fish fish);

}




