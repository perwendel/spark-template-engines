package spark.template.freemarker;

import org.junit.Assert;
import org.junit.Test;
import spark.ModelAndView;

import java.util.HashMap;

public class RenderTest {

    @Test
    public void test() {

        String expected = "<h1>Hello from FreeMarker!</h1>\r\n" +
                "<h2>The above text is rendered using the Spark FreeMarker Template Engine</h2>";

        Assert.assertEquals(expected, new FreeMarkerTemplateEngine().render(new ModelAndView(new HashMap<String, Object>() {{
            put("message", "Hello from FreeMarker!");
        }}, "hello.ftl")));

    }

}
