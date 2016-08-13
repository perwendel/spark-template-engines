Mustache - Spark Template Engine
================================

How to use the Mustache Template Engine for Spark:

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

public class MustacheExample {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Mustache!");

            return new ModelAndView(model, "hello.mustache");
        }, new MustacheTemplateEngine());

    }

}
```
