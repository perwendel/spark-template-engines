package spark.template.handlebars;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import spark.ModelAndView;

import static org.junit.Assert.*;

public class HandlebarsTemplateEngineTest {
    @Test
    public void render() throws Exception {
        String templateVariable = "Hello Handlebars!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new HandlebarsTemplateEngine().render(new ModelAndView(model, "hello.hbs"));
        assertEquals(expected, actual);
    }

    @Test
    public void renderUsingPrefix() throws Exception {
        String templateVariable = "Hello Handlebars!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new HandlebarsTemplateEngine(HandlebarsTemplateEngine.DEFAULT_RESOURCE_ROOT, ".hbs")
                .render(new ModelAndView(model, "hello"));
        assertEquals(expected, actual);
    }

}