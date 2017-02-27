package spark.template.rocker.example;

import spark.template.rocker.RockerTransformer;
import static spark.Spark.get;

public class RockerTransformerExample {

    public static void main(String args[]) {

        get("/hello", (request, response) -> {
	    return views.hello.template("Hello Rocker!");
        }, new RockerTransformer());

    }

}
