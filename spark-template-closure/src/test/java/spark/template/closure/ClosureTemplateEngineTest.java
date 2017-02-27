package spark.template.closure;

import org.junit.Test;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ClosureTemplateEngineTest {
    @Test
    public void testRender() throws Exception {
        final String templateVariable = "Hello Soy!";
        final Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);

        final String expected = String.format("<h1>%s</h1>", templateVariable);

        final String actual = new ClosureTemplateEngine("hello.soy")
                .render(new ModelAndView(model, "spark.template.closure.hello"));

        assertEquals(expected, actual);
    }

}
