package com.panther.ComponentTest.Bean;

import com.panther.springframework.beans.factory.annotation.Autowired;
import com.panther.springframework.beans.factory.annotation.Value;
import com.panther.springframework.context.annotation.Component;

import java.util.Random;

/**
 * @author Gin 琴酒
 * @data 2023/11/1 17:37
 */
@Component("userService")
public class UserService implements IUserService {

    @Value("${token}")
    private String token;


    //private UserDao userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //return userDao.queryUserName("10001") + "，" + token;
        return "八杯水，上海，尖沙咀";
    }

}
