package repository.aggregator.service.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.hateos.JsonError;
import io.micronaut.http.hateos.Link;
import io.reactivex.Flowable;
import repository.aggregator.service.clients.GithubClientInterface;
import repository.aggregator.service.clients.GitlabClientInterface;
import repository.aggregator.service.models.FetchedInfo;

@Controller("/aggregated")
public class AggregatorController {

    private final GithubClientInterface githubClientInterface;
    private final GitlabClientInterface gitlabClientInterface;

    public AggregatorController(GithubClientInterface githubClientInterface,
                                GitlabClientInterface gitlabClientInterface) {
        this.githubClientInterface = githubClientInterface;
        this.gitlabClientInterface = gitlabClientInterface;
    }


    @Get()
    public Flowable<FetchedInfo> index(@QueryValue("q") String queryParam) throws Exception {
        return Flowable.concat(
                githubClientInterface.getRepos(queryParam),
                gitlabClientInterface.getProjects(queryParam)
        );
    }


    @Error
    public HttpResponse<JsonError> generalError(HttpRequest request, Exception exception) {
        JsonError error = new JsonError(exception.getMessage())
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>status(HttpStatus.BAD_GATEWAY, exception.getMessage())
                .body(error);
    }
}