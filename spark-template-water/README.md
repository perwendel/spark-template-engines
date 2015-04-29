spark-template-water
=======================

Spark templating system using [Water](http://watertemplate.org) template engine.

### How to use

```java
import static spark.template.water.WaterTemplateEngine.render;
import static spark.template.water.WaterTemplateEngine.waterEngine;

public class WaterTemplateEngineTest {
    public static void main(String[] args) {
        get("/home", (req, res) -> {
            return render(new HelloPage(), req);
        }, waterEngine());
    }
}

//

public class HelloPage extends Template {
    public HelloPage() { add("water", "Water"); }

    @Override
    protected String getFilePath() { return "hello.water"; }
}
```
