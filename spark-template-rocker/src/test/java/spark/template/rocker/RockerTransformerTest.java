package spark.template.rocker;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RockerTransformerTest {

    @Test
    public void testRender() throws Exception {
        String templateVariable = "Hello Rocker!";
        String expected = String.format("<h1>%s</h1>\n", templateVariable);
        String actual = new RockerTransformer().render(views.hello.template(templateVariable));
	assertEquals(expected, actual);
    }

}
