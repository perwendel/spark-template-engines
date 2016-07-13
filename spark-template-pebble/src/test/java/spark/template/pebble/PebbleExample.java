package spark.template.pebble;

import spark.ModelAndView;

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
			return new ModelAndView(attributes, "templates/hello.pebble");
		}, new PebbleTemplateEngine());
	}
}
