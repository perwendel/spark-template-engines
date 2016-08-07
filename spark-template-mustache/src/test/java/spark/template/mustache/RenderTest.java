package spark.template.mustache;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import spark.ModelAndView;
import spark.utils.IOUtils;

import static spark.Spark.get;
import static spark.Spark.stop;

public class RenderTest {

    private CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

    @Before
    public void before() {
        get("/", (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Hello from Mustache!");
            return new ModelAndView(map, "hello.mustache");
        }, new MustacheTemplateEngine());

    }

    @After
    public void after() throws IOException {
        closeableHttpClient.close();
        stop();
    }

    @Test
    public void test() {
        String expected = "<h1>Hello from Mustache!</h1>\r\n" +
                "<h2>The above text is rendered using the Spark Mustache Template Engine</h2>";

        try {
            HttpResponse httpResponse = closeableHttpClient.execute(new HttpHost("localhost", 4567), new HttpGet("/"));
            InputStream inputStream = httpResponse.getEntity().getContent();
            StringWriter stringWriter = new StringWriter();
            IOUtils.copy(inputStream, stringWriter);
            String body = stringWriter.toString();
            Assert.assertEquals(body, expected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
