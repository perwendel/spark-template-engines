Handlebars - Spark Template Engine
==================================

How to use the Handlebars Template Engine for Spark:

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.get;

public class HandlebarsExample {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Handlebars!");

            return new ModelAndView(model, "hello.hbs");
        }, new HandlebarsTemplateEngine());

    }

}
```
