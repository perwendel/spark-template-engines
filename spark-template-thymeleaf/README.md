spark-template-thymeleaf
=======================

Spark templating system using thymeleaf

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

/**
 * Thymeleaf template engine example
 */
public class ThymeleafTemplateExample {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name", "Sam");

        // hello.html file is in resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello"), new ThymeleafTemplateEngine());
    }
}
```