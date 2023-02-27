package com.Service;

import com.data_object.UserDO;
import com.repository.UserRepo;
import com.view_object.UserVO;
import org.mindrot.jbcrypt.BCrypt;

public class userService {
    private final UserRepo userRepo = new UserRepo();

    public UserVO register(String username, String password) {
        // 1. 针对 password 做好“加密” 【一会儿再处理】
        String salt = BCrypt.gensalt();
        password = BCrypt.hashpw(password, salt);

        // 2. 将数据插入到 数据库的 users 表中 —— 通过 repository 对象完成（UserRepo)
        UserDO userDO = new UserDO(username, password);
        userRepo.insert(userDO);    // 真正进行 SQL 操作

        return new UserVO(userDO);
    }

    public UserVO login(String username, String password) {
        // 1. 先根据用户名从数据库表中查出对应的记录 —— userRepo 得到 userDO
        UserDO userDO = userRepo.selectOneByUsername(username);
        if (userDO == null) {
            // 说明，根据用户名查不到记录，直接登录失败
            return null;
        }

        // 2. 判断密码是否正确
        // 第一个参数：明文密码 —— 来自用户输入的密码，也就是参数中的 password
        // 第二个参数：hash 后的密码 —— 来自数据库的表中保持的密码，也就是 userDO.password
        if (!BCrypt.checkpw(password, userDO.password)) {
            // 代表密码没有匹配中（hash 值没有匹配上）
            return null;
        }

        // UserDO -> UserVO
        return new UserVO(userDO);
    }
}
