package net.codebrewery.pensieve.health;

import com.hubspot.dropwizard.guice.InjectableHealthCheck;

public class PingHealthCheck extends InjectableHealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

    @Override
    public String getName() {
        return "PingHealthCheck";
    }
}
