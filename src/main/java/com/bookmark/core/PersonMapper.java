package com.bookmark.core;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements ResultSetMapper<Users> {
    @Override
    public Users map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Users(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"));
    }
}
