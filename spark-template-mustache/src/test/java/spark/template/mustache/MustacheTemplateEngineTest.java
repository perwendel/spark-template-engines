package spark.template.mustache;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import spark.ModelAndView;

import static org.junit.Assert.*;

public class MustacheTemplateEngineTest {
    @Test
    public void test_render() throws Exception {
        String templateVariable = "Hello Mustache!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new MustacheTemplateEngine().render(new ModelAndView(model, "hello.mustache"));
        assertEquals(expected, actual);
    }

}