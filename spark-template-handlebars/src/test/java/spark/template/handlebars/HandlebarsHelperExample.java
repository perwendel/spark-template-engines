package spark.template.handlebars;

import spark.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class HandlebarsHelperExample extends HandlebarsTemplateEngine {

    public HandlebarsHelperExample() {
        super();
        handlebars.registerHelpers(this);
    }

    // Example of a helper, write {{now}} in template file.
    public String now() {
        return new Date().toString();
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name", "Chris");

        // hello.hbs file is in resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello.hbs"),
                new HandlebarsHelperExample());

    }
}
