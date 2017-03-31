package async;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by John H on 2/25/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUser {
    private String name;
    private String blog;

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getBlog () {
        return this.blog;
    }

    public void setBlog (String blog) {
        this.blog = blog;
    }

    @Override
    public String toString () {
        return "User [name=" + name + ", blog=" + blog + "]";
    }

}
