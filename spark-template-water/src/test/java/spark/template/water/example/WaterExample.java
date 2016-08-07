package spark.template.water.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.water.WaterTemplateEngine;

import static spark.Spark.get;

public class WaterExample {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Water!");

            return new ModelAndView(model, "hello.water");
        }, new WaterTemplateEngine());

    }

}
