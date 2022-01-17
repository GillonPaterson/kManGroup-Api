package com.kainos.ea;

import com.kainos.ea.controller.BandLevel;
import com.kainos.ea.controller.Capability;
import com.kainos.ea.controller.Competency;
import com.kainos.ea.controller.JobFamilies;
import com.kainos.ea.controller.JobRoles;
import com.kainos.ea.controller.Login;
import com.kainos.ea.controller.Training;
import com.kainos.ea.util.CoreAuthorizer;
import com.kainos.ea.util.OAuth2Authenticator;
import com.kainos.ea.util.User;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class WebServiceApplication extends Application<WebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new WebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "Employee";
    }

    @Override
    public void initialize(final Bootstrap<WebServiceConfiguration> bootstrap) {
        //TODO:. application initialization
        bootstrap.addBundle(new SwaggerBundle<WebServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(WebServiceConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final WebServiceConfiguration configuration,
                    final Environment environment) {
        //TODO:. implement application

        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new OAuth2Authenticator())
                        .setAuthorizer(new CoreAuthorizer())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        environment.jersey().register(new Login());
        environment.jersey().register(new BandLevel());
        environment.jersey().register(new Capability());
        environment.jersey().register(new Competency());
        environment.jersey().register(new JobFamilies());
        environment.jersey().register(new JobRoles());
        environment.jersey().register(new Training());
        environment.jersey().register(new Login());
    }

}
