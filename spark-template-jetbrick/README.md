Jetbrick - Spark Template Engine
================================

How to use the Jetbrick Template Engine for Spark:

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.jetbrick.JetbrickTemplateEngine;

import static spark.Spark.get;

public class JetbrickExample {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Jetbrick!");

            return new ModelAndView(model, "hello.jetx");
        }, new JetbrickTemplateEngine());

    }

}
```
