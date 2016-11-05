package spark.template.pebble;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

/**
 * @author Nikki
 */
public class PebbleExample {

    public static void main(String[] args) {

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello World!");
            return new ModelAndView(model, "templates/hello.pebble"); // located in resources/spark/template/pebble
        }, new PebbleTemplateEngine());

    }
}
