Jinjava - Spark Template Engine
===============================

How to use the Jinjava template route for Spark example:

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

public class JinjavaExample {

    public static void main(String[] args) {
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Jinjava!");

            return new ModelAndView(model, "hello.jin");
        }, new JinjavaTemplateEngine());

    }

}
```
