package spark.template.jetbrick;

import org.junit.Assert;
import org.junit.Test;
import spark.ModelAndView;

import java.util.HashMap;

public class RenderTest {

    @Test
    public void test() {

        String expected = "<h1>Hello from Jetbrick!</h1>\r\n" +
                "<h2>The above text is rendered using the Spark Jetbrick Template Engine</h2>";

        Assert.assertEquals(expected, new JetbrickTemplateEngine().render(new ModelAndView(new HashMap<String, Object>() {{
            put("message", "Hello from Jetbrick!");
        }}, "hello.jetx")));

    }

}
