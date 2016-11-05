package spark.template.thymeleaf;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import spark.ModelAndView;

import static org.junit.Assert.*;

public class ThymeleafTemplateEngineTest {

    @Test
    public void test_render() throws Exception {
        String templateVariable = "Hello Thymeleaf!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new ThymeleafTemplateEngine().render(new ModelAndView(model, "hello"));
        assertEquals(expected, actual);
    }

}