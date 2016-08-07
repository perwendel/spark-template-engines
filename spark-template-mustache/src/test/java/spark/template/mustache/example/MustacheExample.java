package spark.template.mustache.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

public class MustacheExample {

    public static void main(String[] args) {

        Spark.get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Mustache!");

            return new ModelAndView(model, "hello.mustache");
        }, new MustacheTemplateEngine());

    }

}
