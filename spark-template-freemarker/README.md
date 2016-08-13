Freemarker - Spark Template Engine
==================================

How to use the FreeMarker Template Engine for Spark:

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerTemplateEngine;

import static spark.Spark.get;

public class FreeMarkerExample {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from FreeMarker!");

            return new ModelAndView(model, "hello.ftl");
        }, new FreeMarkerTemplateEngine());

    }

}
```
