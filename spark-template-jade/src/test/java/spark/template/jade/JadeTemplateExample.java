package spark.template.jade;

import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class JadeTemplateExample {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name", "Godzilla");

        // hello.jade file is in resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello"), new JadeTemplateEngine());
    }
}
