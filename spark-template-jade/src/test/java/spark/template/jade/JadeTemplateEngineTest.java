package spark.template.jade;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import spark.ModelAndView;

import static org.junit.Assert.*;

/**
 * Created by David on 05.11.2016.
 */
public class JadeTemplateEngineTest {
    @Test
    public void test_render() throws Exception {
        String templateVariable = "Hello Jade!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new JadeTemplateEngine().render(new ModelAndView(model, "hello.jade"));
        assertEquals(expected, actual);
    }

}