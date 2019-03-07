package repository.aggregator.service.stubs;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Flowable;
import repository.aggregator.service.clients.GithubClientInterface;
import repository.aggregator.service.models.RepoInfo;

import javax.inject.Singleton;

@Requires(env = Environment.TEST)
@Fallback
@Singleton
public class GitHubClientStub implements GithubClientInterface {

    @Override
    public Flowable<RepoInfo> getRepos(String queryParam) throws Exception {
        Flowable<RepoInfo> result = null;
        if (queryParam.equals("emptyQuery")) {
            return Flowable.empty();
        } else if (queryParam.equals("gitHubErrorQuery")) {
            throw new Exception("Unexpected error occured in Github repo fetching.");
        } else {
            return Flowable.just(new RepoInfo(
                    "Example Github Repo",
                    "owner",
                    "url",
                    "desc",
                    "lan"));
        }
    }
}