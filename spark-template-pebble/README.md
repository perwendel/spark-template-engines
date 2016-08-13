Pebble - Spark Template Engine
==============================

How to use the Pebble template route for Spark example:

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

public class PebbleExample {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Pebble!");

            return new ModelAndView(model, "hello.pebble");
        }, new PebbleTemplateEngine());

    }

}
```
