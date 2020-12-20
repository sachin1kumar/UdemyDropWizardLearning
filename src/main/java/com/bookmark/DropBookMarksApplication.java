package com.bookmark;

import com.bookmark.auth.HelloAuthenticator;
import com.bookmark.core.User;
import com.bookmark.resources.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropBookMarksApplication extends Application<DropBookMarksConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropBookMarksApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropBookMarks";
    }

    @Override
    public void initialize(final Bootstrap<DropBookMarksConfiguration> bootstrap) {
        //For DB migration..
        bootstrap.addBundle(new MigrationsBundle<DropBookMarksConfiguration>() {

            public PooledDataSourceFactory getDataSourceFactory(DropBookMarksConfiguration dropBookMarksConfiguration) {
                return dropBookMarksConfiguration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final DropBookMarksConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new HelloResource());

        //For basic authentication of API..
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new HelloAuthenticator(configuration.getPassword()))
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));


    }

}
