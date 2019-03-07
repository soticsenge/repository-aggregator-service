package repository.aggregator.service.clients;

import io.reactivex.Flowable;
import repository.aggregator.service.models.ProjectInfo;

public interface GitlabClientInterface {
    Flowable<ProjectInfo> getProjects(String queryParam) throws Exception;
}