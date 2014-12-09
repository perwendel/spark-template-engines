package spark.template.jetbrick;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

/**
 * spark-template-jetbrick example
 * @author kute
 */
public class JetbrickTemplateExample {
	
	public static void main(String[] args) {
		
		get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            
            model.put("target", "spark-template-jetbrick");

            return new ModelAndView(model, "template/hello.jetx");
        }, new JetTemplateEngine());
		
	}

}
