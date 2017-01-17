package spark.template.rocker;

import com.fizzed.rocker.BindableRockerModel;
import com.fizzed.rocker.Rocker;
import com.fizzed.rocker.runtime.RockerRuntime;
import org.junit.Assert;
import org.junit.Test;

public class RockerTemplateTest extends Assert {
    @Test
    public void rendering() {
        RockerRuntime.getInstance().setReloading(true);
        BindableRockerModel template = Rocker.template(
                "rocker/views/ExampleTemplate.rocker.html", "Hello World!");
        RockerTemplateModel templateModel = new RockerTemplateModel(template);
        String actual = RockerTemplateEngine.getInstance().render(templateModel);
        String expected = "<h1>Hello World!</h1>\n";
        assertEquals(expected, actual);
    }
}
