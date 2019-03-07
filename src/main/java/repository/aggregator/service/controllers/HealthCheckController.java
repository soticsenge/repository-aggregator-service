package repository.aggregator.service.controllers;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.context.ServerRequestContext;
import repository.aggregator.service.models.HealthCheckInfo;


@Controller("/healthCheck")
public class HealthCheckController {
    @Value(value = "${version}")
    private String version;

    @Get(produces = MediaType.APPLICATION_JSON)
    public HealthCheckInfo index() {
        String host = ServerRequestContext
                .currentRequest()
                .map(HttpRequest::getServerName)
                .orElse("unkown");
        return new HealthCheckInfo(version, host);
    }
}
