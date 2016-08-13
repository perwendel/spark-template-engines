package spark.template.thymeleaf.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.get;

public class ThymeleafExample {

    public static void main(final String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Thymeleaf!");

            return new ModelAndView(model, "hello.html");
        }, new ThymeleafTemplateEngine());

    }

}