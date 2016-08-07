package spark.template.jade.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.jade.JadeEngine;

import static spark.Spark.get;

public class JadeExample {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Jade!");

            return new ModelAndView(model, "hello.jade");
        }, new JadeEngine());

    }

}
