package async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.RestTemplate;

/**
 * Created by John H on 2/25/17.
 */
@Service
public class GitHubService <T> {

    @Autowired
    private final RestTemplate template;

    public GitHubService (RestTemplateBuilder builder) {
        this.template = builder.build();
    }

    @Async
    public ListenableFuture<GitHubUser> findGitHubUser (String username) throws InterruptedException {
        System.err.println( "Looking up "+ username +" on GitHub");
        String url = String.format("https://api.github.com/users/%s", username);
        GitHubUser user = template.getForObject(url, GitHubUser.class);
        Thread.sleep(1000);
        return new AsyncResult<GitHubUser>(user);
    }

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

}