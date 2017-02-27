spark-template-rocker 
==============================================

2 ways to use [Rocker](https://github.com/fizzed/rocker) templates in Spark:

1) Using the template class with the ResponseTransformer:

```java
import spark.template.rocker.RockerTransformer;
import static spark.Spark.get;

public class RockerTransformerExample {

    public static void main(String args[]) {

        get("/hello", (request, response) -> {
	        return views.hello.template("Hello Rocker!");
        }, new RockerTransformer());

    }

}
```

2) Using the template name with the TemplateEngine:

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.rocker.RockerEngine;
import static spark.Spark.get;

public class RockerEngineExample {

    public static void main(String args[]) {

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Rocker!");
            return new ModelAndView(model, "views/hello.rocker.html");
        }, new RockerEngine());

    }

}
```
