spark-template-mvel
======================

How to use the [MVEL](https://github.com/mvel/mvel) template route for Spark example:


```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

public class MvelTemplateEngineExample {

    public static void main(String[] args) {
        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Mvel!");
            // hello.html file is in resources/templates directory
            return new ModelAndView(model, "hello.html");
        }, new MvelTemplateEngine());
    }

}
```
