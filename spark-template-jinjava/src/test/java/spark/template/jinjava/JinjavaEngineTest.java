package spark.template.jinjava;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import spark.ModelAndView;

import static org.junit.Assert.*;

public class JinjavaEngineTest {
    @Test
    public void test_render() throws Exception {
        String templateVariable = "Hello Jinjava!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new JinjavaEngine().render(new ModelAndView(model, "template/hello.jin"));
        assertEquals(expected, actual);
    }

}