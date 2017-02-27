package spark.template.rocker;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RockerEngineTest {

    @Test
    public void testRender() throws Exception {
        String templateVariable = "Hello Rocker!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>\n", templateVariable);
        String actual = new RockerEngine().render(new ModelAndView(model, "views/hello.rocker.html"));
	assertEquals(expected, actual);
    }

}
