package repository.aggregator.service.stubs;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Flowable;
import repository.aggregator.service.clients.GitlabClientInterface;
import repository.aggregator.service.models.ProjectInfo;
import repository.aggregator.service.models.RepoInfo;

import javax.inject.Singleton;

@Requires(env = Environment.TEST)
@Fallback
@Singleton
public class GitlabClientStub implements GitlabClientInterface {


    @Override
    public Flowable<ProjectInfo> getProjects(String queryParam) throws Exception {
        Flowable<RepoInfo> result = null;
        if (queryParam.equals("emptyQuery")) {
            return Flowable.empty();
        } else if (queryParam.equals("gitlabErrorQuery")) {
            throw new Exception("Unexpected error occured in Gitlab repo fetching.");
        } else {
            return Flowable.just(new ProjectInfo(
                    "Example Gitlab Repo",
                    "owner",
                    "url",
                    "desc"));
        }
    }
}