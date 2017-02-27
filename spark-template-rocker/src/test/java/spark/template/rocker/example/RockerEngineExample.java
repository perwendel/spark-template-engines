package spark.template.rocker.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.rocker.RockerEngine;
import static spark.Spark.get;

public class RockerEngineExample {

    public static void main(String args[]) {

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Rocker!");
            return new ModelAndView(model, "views/hello.rocker.html");
        }, new RockerEngine());

    }

}
