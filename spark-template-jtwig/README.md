spark-template-jtwig
====================

Spark templating system using Jtwig.

```java
import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

public class JtwigTemplateExample {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name", "Rafa");

        // hello.twig file is in resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello.twig"), new JtwigTemplateEngine());
    }
}
```