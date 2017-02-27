spark-template-rocker
=======================

Spark templating system using [Rocker](https://github.com/fizzed/rocker)

```java
import rocker.views.ExampleTemplate;

import static spark.Spark.get;

/**
 * Create a web app with one page /hello that renders using Rocker
 *
 * https://github.com/fizzed/rocker
 */
public class RockerExample {
    public static void main(String[] args) {
        get("/hello",
                (req, res) -> {
                    ExampleTemplate template = ExampleTemplate.template("Hello World!");
                    return new RockerTemplateModel(template);
                },
                RockerTemplateEngine.getInstance());
    }
}
```