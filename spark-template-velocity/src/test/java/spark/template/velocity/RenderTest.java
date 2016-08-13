package spark.template.velocity;

import org.junit.Assert;
import org.junit.Test;
import spark.ModelAndView;

import java.util.HashMap;

public class RenderTest {

    @Test
    public void test() {

        String expected = "<h1>Hello from Velocity!</h1>\r\n" +
                "<h2>The above text is rendered using the Spark Velocity Template Engine</h2>";

        Assert.assertEquals(expected, new VelocityTemplateEngine().render(new ModelAndView(new HashMap<String, Object>() {{
            put("message", "Hello from Velocity!");
        }}, "hello.vm")));

    }

}
