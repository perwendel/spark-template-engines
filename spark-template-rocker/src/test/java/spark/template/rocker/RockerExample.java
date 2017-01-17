package spark.template.rocker;

import rocker.views.ExampleTemplate;

import static spark.Spark.get;

public class RockerExample {
    public static void main(String[] args) {
        get("/hello",
                (req, res) -> {
                    ExampleTemplate template = ExampleTemplate.template("Hello World!");
                    return new RockerTemplateModel(template);
                },
                RockerTemplateEngine.getInstance());
    }
}
