#spark-template-closure

Spark templating system using [Google's closure template](https://developers.google.com/closure/templates/).

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.closure.ClosureTemplateEngine;

import static spark.Spark.get;

/**
 * Closure template engine example
 */
public class ClosureTemplateExample {
    public static void main(String[] args) {
        Map<String, ?> map = new HashMap<>();
        map.put("name", "Prasanna");

        get("/hello",
            (req, res) -> new ModelAndView(map, "spark.template.closure.hello"),
            new ClosureTemplateEngine("./src/test/resources/templates/hello.soy"));
    }
}
```
