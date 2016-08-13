package spark.template.jade;

import org.junit.Assert;
import org.junit.Test;
import spark.ModelAndView;

import java.util.HashMap;

public class RenderTest {

    @Test
    public void test() {

        String expected = "<h1>Hello from Jade!</h1><h2>The above text is rendered using the Spark Jade Engine</h2>";

        Assert.assertEquals(expected, new JadeEngine().render(new ModelAndView(new HashMap<String, Object>() {{
            put("message", "Hello from Jade!");
        }}, "hello.jade")));

    }

}
