package repository.aggregator.service.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class RepoInfo extends FetchedInfo {
    private String language;

    @JsonCreator
    public RepoInfo(@JsonProperty("fullName") String fullName,
                    @JsonProperty("owner") String owner,
                    @JsonProperty("url") String url,
                    @JsonProperty("description") String description,
                    @JsonProperty("language") String language) {
        super(fullName, owner, url, description);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

