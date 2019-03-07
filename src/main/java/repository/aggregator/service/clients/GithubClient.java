package repository.aggregator.service.clients;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import repository.aggregator.service.models.RepoInfo;

@Client("http://localhost:8080")
@Requires(notEnv = Environment.TEST)
public interface GithubClient extends GithubClientInterface {

    @Get("/repos")
    Flowable<RepoInfo> getRepos(@QueryValue("q") String queryParam) throws Exception;

}