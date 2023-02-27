package com.repository;

import com.data_object.UserDO;
import com.util.Dbutil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// 这里执行真正的 SQL
public class UserRepo {
    @SneakyThrows
    public void insert(UserDO userDO) {
        String sql = "insert into users (username, password) values (?, ?)";
        try (Connection c = Dbutil.connection()) {
            try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, userDO.username);
                ps.setString(2, userDO.password);

                ps.executeUpdate(); // 执行 SQL

                // 得到插入的自增 id 作为 userDO 的 uid
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    userDO.uid = rs.getInt(1);
                }
            }
        }
    }

    @SneakyThrows
    public UserDO selectOneByUsername(String username) {
        String sql = "select uid, username, password from users where username = ?";
        try (Connection c = Dbutil.connection()) {
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        // 说明一条记录都没有查到
                        return null;
                    }

                    return new UserDO(
                            rs.getInt("uid"),
                            rs.getString("username"),
                            rs.getString("password")
                    );
                }
            }
        }
    }
}
