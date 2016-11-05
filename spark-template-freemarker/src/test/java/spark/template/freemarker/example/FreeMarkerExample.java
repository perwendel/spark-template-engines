package spark.template.freemarker.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.get;

public class FreeMarkerExample {

    public static void main(String args[]) {

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Freemarker!");
            return new ModelAndView(model, "hello.ftl"); // located in src/test/resources/spark/template/freemarker
        }, new FreeMarkerEngine());

    }

}