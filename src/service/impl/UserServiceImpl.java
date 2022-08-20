package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.*;
import service.UserService;

import java.util.List;
import java.util.Vector;

public class UserServiceImpl implements UserService {

    static UserDao userDao = new UserDaoImpl();

    @Override
    public String registe(String username, String password) {
        //判断用户名和密码是否存在空串、过长的问题
        if ("".equals(username) || username == null){
            return "用户名不能为空";
        }
        if ("".equals(password) || password == null){
            return "密码不能为空";
        }
        if (username.length() > 10){
            return "用户名过长";
        }
        if (password.length() > 15){
            return "密码过长";
        }
        //查询是否存在同名用户
        User isExsits = userDao.findByUsername(username);
        //如果不存在，则注册
        if (isExsits == null){
            //封装成user对象
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            //添加到数据库（注册）
            userDao.add(user);
            //初始积分1000
            User newUser = userDao.findByUsername(username);
            userDao.addPoint(1000,newUser.getId());
            //给初始鱼塘
            userDao.addFishPond(newUser);

            return "注册成功";
        }else{
            //如果存在，则注册失败
            return "用户名已存在";
        }
    }

    @Override
    public User login(String username, String password) {
        //通过账号密码查询，如果查到了就返回用户，否则null
        return userDao.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public void singIn(User user) {

    }

    @Override
    public List<User> rank() {

        return userDao.rank();
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public void addexp(int id, Fish fish) {

    }

    public static void main(String[] args) {
        System.out.println(userDao.rank());
    }


}
