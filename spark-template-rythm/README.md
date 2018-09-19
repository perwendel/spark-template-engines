spark-template-rythm
==============================================

How to use the Rythm template route for Spark example:

```java
import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.template.rythm.RythmTemplateEngine;

/**
 * RythmTemplateRoute example.
 */
public final class RythmExample {
    
    public static void main(final String[] args) {

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("hello", "Rythm World");
            model.put("person", new Person("Foobar"));

            // The vm files are located under the resources directory
            return new ModelAndView(model, "hello.html");
        }, new RythmTemplateEngine(getRythmProperties()));

    }

    private static RythmProperties getRythmProperties() {
        File f = new File(".");
        String ROOT_DIR = f.getAbsolutePath() + "/src/test/resources/";

		RythmProperties properties = new RythmProperties();
		properties.setTemplateDirectory(ROOT_DIR);
		properties.setTmpDirectory(ROOT_DIR + "/tmp");
		properties.setEngineFileWriteEnabled(true);
		properties.setRythmEngineMode(Rythm.Mode.dev);

		return properties;
    }
    
    public static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
```
