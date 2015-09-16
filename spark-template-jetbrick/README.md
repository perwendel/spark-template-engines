spark-template-jetbrick 
==============================================

How to use the Jetbrick-Template route for Spark example:

```java
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.jetbrick.JetbrickTemplateEngine;

import static spark.Spark.get;

/**
 * spark-template-jetbrick example
 */
public class JetbrickTemplateExample {
	
	public static void main(String[] args) {
		
		get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            
            model.put("target", "spark-template-jetbrick");

            return new ModelAndView(model, "template/hello.jetx");
        }, new JetbrickTemplateEngine());
		
	}

}
```

Jetbrick-Template refer:[https://github.com/subchen/jetbrick-template-2x](https://github.com/subchen/jetbrick-template-2x)
