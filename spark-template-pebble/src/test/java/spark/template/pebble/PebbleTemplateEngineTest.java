package spark.template.pebble;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import spark.ModelAndView;

import static org.junit.Assert.*;

public class PebbleTemplateEngineTest {

    @Test
    public void test_render() throws Exception {
        String templateVariable = "Hello Pebble!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new PebbleTemplateEngine().render(new ModelAndView(model, "templates/hello.pebble"));
        assertEquals(expected, actual);
    }

}