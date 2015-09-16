spark-template-mustache
=======================

Spark templating system using mustache.java

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;

/**
 * Mustache template engine example
 */
public class MustacheTemplateExample {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name", "Sam");

        // hello.mustache file is in resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello.mustache"), new MustacheTemplateEngine());
    }
}
```