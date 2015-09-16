package spark.template.jetbrick;

import java.io.StringWriter;
import java.util.Map;

import jetbrick.template.JetContext;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * JetTemplateEngine  based on Jetbrick-template
 * @see https://github.com/subchen/jetbrick-template-2x
 */
public class JetTemplateEngine extends TemplateEngine {
	
	private JetEngine jetEngine;

	public JetTemplateEngine() {
		jetEngine = JetEngine.create();
	}
	
	public JetTemplateEngine(JetEngine jetEngine) {
		if(null == jetEngine){
			throw new IllegalArgumentException("jetEngine must not be null");
		}
		this.jetEngine = jetEngine;
	}

	@Override
	public String render(ModelAndView modelAndView) {
		JetTemplate template = jetEngine.getTemplate(modelAndView.getViewName());
        Object model = modelAndView.getModel();
        if (model instanceof Map) {
            Map<String, Object> modelMap = (Map<String, Object>) model;
            JetContext context = new JetContext(modelMap.size());
            context.putAll(modelMap);
            StringWriter writer = new StringWriter();
            template.render(context, writer);
            return writer.toString();
        } else {
            throw new IllegalArgumentException("modelAndView must be of type java.util.Map");
        }
	}

}
