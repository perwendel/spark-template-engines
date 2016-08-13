package spark.template.pebble;

import org.junit.Assert;
import org.junit.Test;
import spark.ModelAndView;

import java.util.HashMap;

public class RenderTest {

    @Test
    public void test() {

        String expected = "<h1>Hello from Pebble!</h1>\r\n" +
                "<h2>The above text is rendered using the Spark Pebble Template Engine</h2>";

        Assert.assertEquals(expected, new PebbleTemplateEngine().render(new ModelAndView(new HashMap<String, Object>() {{
            put("message", "Hello from Pebble!");
        }}, "hello.pebble")));

    }

}
