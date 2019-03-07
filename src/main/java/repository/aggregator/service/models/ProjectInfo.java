package repository.aggregator.service.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public  class ProjectInfo extends FetchedInfo {

    @JsonCreator
    public ProjectInfo(@JsonProperty("fullName") String fullName,
                       @JsonProperty("owner") String owner,
                       @JsonProperty("url") String url,
                       @JsonProperty("description") String description) {
        super(fullName, owner, url, description);
    }
}
