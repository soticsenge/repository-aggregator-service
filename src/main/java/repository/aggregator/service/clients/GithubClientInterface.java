package repository.aggregator.service.clients;

import io.reactivex.Flowable;
import repository.aggregator.service.models.RepoInfo;

public interface GithubClientInterface {
    Flowable<RepoInfo> getRepos(String queryParam) throws Exception;
}