package com.bookmark.db;

import com.bookmark.core.PersonMapper;
import com.bookmark.core.Users;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(PersonMapper.class)
public interface JdbiEntityRepository {

    @SqlUpdate("INSERT INTO users (id, username, password) VALUES (:id, :username, :password)")
    void addUser(@BindBean Users user);

    @SqlQuery("SELECT * FROM users WHERE username = :username")
    List<Users> getUsersList(@Bind("username") String username);

}
