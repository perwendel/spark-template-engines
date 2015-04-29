package spark.template.water;

import static spark.Spark.get;
import static spark.template.water.WaterTemplateEngine.render;
import static spark.template.water.WaterTemplateEngine.waterEngine;

public class WaterTemplateEngineTest {

    public static void main(String[] args) {
        get("/home", (req, res) -> render(new HelloPage(), req), waterEngine());
    }
}
