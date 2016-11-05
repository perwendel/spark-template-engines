package spark.template.jetbrick;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import spark.ModelAndView;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 05.11.2016.
 */
public class JetbrickTemplateEngineTest {
    @Test
    public void test_render() throws Exception {
        String templateVariable = "Hello Jetbrick!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new JetbrickTemplateEngine().render(new ModelAndView(model, "template/hello.jetx"));
        assertEquals(expected, actual);
    }

}