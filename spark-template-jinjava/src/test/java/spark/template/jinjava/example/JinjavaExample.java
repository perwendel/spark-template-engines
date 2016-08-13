package spark.template.jinjava.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Spark;
import spark.template.jinjava.JinjavaTemplateEngine;

public class JinjavaExample {

    public static void main(String[] args) {

        Spark.get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Jinjava!");

            return new ModelAndView(model, "hello.jin");
        }, new JinjavaTemplateEngine());

    }

}
