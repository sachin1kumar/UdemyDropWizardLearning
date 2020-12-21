package com.bookmark.db;

import com.bookmark.core.PersonMapper;
import com.bookmark.core.Users;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

@RegisterMapper(PersonMapper.class)
public interface JdbiEntityRepository {

    @SqlUpdate("INSERT INTO users (id, username, password) VALUES (:id, :username, :password)")
    void addUser(@BindBean Users user);

}
