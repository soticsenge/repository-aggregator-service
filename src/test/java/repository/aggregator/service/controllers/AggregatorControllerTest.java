package repository.aggregator.service.controllers;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxStreamingHttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.reactivex.Flowable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repository.aggregator.service.models.FetchedInfo;

import static io.micronaut.http.HttpStatus.BAD_GATEWAY;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class AggregatorControllerTest {
    private static EmbeddedServer server;
    private static RxStreamingHttpClient client;



    @BeforeClass
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server
                .getApplicationContext()
                .createBean(RxStreamingHttpClient.class, server.getURL());
    }

    @AfterClass
    public static void stopServer() {
        if (server != null) {
            server.stop();
        }
        if (client != null) {
            client.stop();
        }
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testRetrieveReposEmpty() throws Exception {
        Flowable<FetchedInfo> repos = client.jsonStream(HttpRequest.GET("/aggregated?q=emptyQuery"), FetchedInfo.class);
        assertEquals(repos.toList().blockingGet().size(), 0);
    }

    @Test
    public void testRetrieveReposNonEmpty() throws Exception {
        Flowable<FetchedInfo> repos = client.jsonStream(HttpRequest.GET("/aggregated?q=properQuery"), FetchedInfo.class);
        assertEquals(repos.toList().blockingGet().size(), 2);
        assertEquals(repos.toList().blockingGet().get(0).getFullName(), "Example Github Repo");
        assertEquals(repos.toList().blockingGet().get(1).getFullName(), "Example Gitlab Repo");
    }

    @Test
    public void testHandleGitlabServiceError() throws Exception {
        exception.expect(HttpClientResponseException.class);
        exception.expectMessage("Unexpected error occured in Gitlab repo fetching.");
        exception.expect(hasProperty("response", hasProperty("status", is(BAD_GATEWAY))));

        HttpRequest request = HttpRequest.GET("/aggregated?q=gitlabErrorQuery");
        client.toBlocking().exchange(request);
    }

    @Test
    public void testHandleGithubServiceError() throws Exception {
        exception.expect(HttpClientResponseException.class);
        exception.expectMessage("Unexpected error occured in Github repo fetching.");
        exception.expect(hasProperty("response", hasProperty("status", is(BAD_GATEWAY))));

        HttpRequest request = HttpRequest.GET("/aggregated?q=gitHubErrorQuery");
        client.toBlocking().exchange(request);
    }
}