package com.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DropBookMarksConfiguration extends Configuration {
    // TODO: implement service configuration

    @NotEmpty  //password val should not be empty in yml.
    private String password;

    @NotNull //must be present and valid.
    @Valid
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty   //to deserialize the value present in yml.
    public String getPassword() {
        return password;
    }

    @JsonProperty("database") //must be same as mentioned in config.yml..
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

}
