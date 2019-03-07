package repository.aggregator.service.clients;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import repository.aggregator.service.models.ProjectInfo;

@Client("http://localhost:8081")
@Requires(notEnv = Environment.TEST)
public interface GitlabClient extends GitlabClientInterface {

    @Get("/projects")
    Flowable<ProjectInfo> getProjects(@QueryValue("q") String queryParam) throws Exception;

}