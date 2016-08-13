package spark.template.handlebars;

import org.junit.Assert;
import org.junit.Test;
import spark.ModelAndView;

import java.util.HashMap;

public class RenderTest {

    @Test
    public void test() {

        String expected = "<h1>Hello from Handlebars!</h1>\r\n" +
                "<h2>The above text is rendered using the Spark Handlebars Template Engine</h2>";

        Assert.assertEquals(expected, new HandlebarsTemplateEngine().render(new ModelAndView(new HashMap<String, Object>() {{
            put("message", "Hello from Handlebars!");
        }}, "hello.hbs")));

    }

}
