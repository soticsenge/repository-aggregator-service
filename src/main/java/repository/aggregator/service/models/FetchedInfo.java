package repository.aggregator.service.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = RepoInfo.class, name = "GITHUB"),
        @JsonSubTypes.Type(value = ProjectInfo.class, name = "GITLAB")
})
public abstract class FetchedInfo {
    private String fullName;
    private String owner;
    private String url;
    private String description;

    protected FetchedInfo(String fullName, String owner, String url, String description) {
        this.fullName = fullName;
        this.owner = owner;
        this.url = url;
        this.description = description;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
