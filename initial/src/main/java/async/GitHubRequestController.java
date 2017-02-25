package async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Future;

/**
 * Created by revlin on 2/25/17.
 */
@Component
@RestController
@RequestMapping("/")
public class GitHubRequestController {

    @Autowired
    private GitHubService gitHubService;

    @RequestMapping(value="/{username}")
    public DeferredResult<GitHubUser> getUserByName (@PathVariable String username) {
        DeferredResult<GitHubUser> result = new DeferredResult<GitHubUser>();
        ListenableFuture<GitHubUser> user;
        long startTime = System.currentTimeMillis();

        System.err.println( "Processing request to find "+ username +" on GitHub");

        try {
            user = gitHubService.findGitHubUser(username);
            user.addCallback(new ListenableFutureCallback<GitHubUser>() {

                @Override
                public void onSuccess(GitHubUser gitHubUser) {
                    result.setResult(gitHubUser);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    result.setErrorResult(throwable.getMessage());
                }
            });
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }


        return result;
    }
}
