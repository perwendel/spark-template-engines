spark-template-pebble
==============================================

How to use the Pebble template route for Spark example:

```java
import spark.ModelAndView;
import spark.template.pebble.PebbleTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * @author Nikki
 */
public class PebbleExample {

	public static void main(String[] args) {
		get("/hello", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("message", "Hello World!");

			// The hello.pebble file is located in directory:
			// src/test/resources/spark/template/pebble
			return new ModelAndView(attributes, "hello.pebble");
		}, new PebbleTemplateEngine());
	}
}
```
