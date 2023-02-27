package com.data_object;

import lombok.Data;

@Data
public class UserDO {
    public Integer uid;
    public String username;
    public String password;

    public UserDO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDO(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }
}
