package spark.template.velocity.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.get;

public final class VelocityExample {

    public static void main(final String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Velocity!");

            return new ModelAndView(model, "hello.vm");
        }, new VelocityTemplateEngine());

    }

}
