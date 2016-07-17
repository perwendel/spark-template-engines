spark-template-jinjava
======================

How to use the Jinjava template route for Spark example:

```java
import spark.ModelAndView;
import spark.template.pebble.PebbleTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class PebbleExample {

	public static void main(String[] args) {
		get("/hello", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("message", "spark-template-jinjava");
			return new ModelAndView(attributes, "template\hello.jin");
		}, new JinjavaTemplateEngine());
	}
}
```
