package spark.template.water;

import org.watertemplate.Template;

public class HelloPage extends Template {
    public HelloPage() {
        add("water", "Water");
    }

    @Override
    protected String getFilePath() {
        return "hello.water";
    }
}
