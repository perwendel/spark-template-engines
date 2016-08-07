package spark.template.velocity;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import spark.ModelAndView;
import spark.TemplateEngine;

public class VelocityTemplateEngine extends TemplateEngine {

    private VelocityEngine velocityEngine;
    private String templateRoot;

    /**
     * Constructor
     */
    public VelocityTemplateEngine() {
        this("templates/");
    }

    public VelocityTemplateEngine(String templateRoot) {
        this(templateRoot, new Properties() {{
            setProperty("resource.loader", "class");
            setProperty("class.resource.loader.class",
                        "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        }});
    }

    public VelocityTemplateEngine(String templateRoot, Properties properties) {
        this(templateRoot, new VelocityEngine(properties));
    }

    /**
     * Constructor
     *
     * @param velocityEngine The velocity engine, must not be null.
     */
    public VelocityTemplateEngine(String templateRoot, VelocityEngine velocityEngine) {
        if (velocityEngine == null) {
            throw new IllegalArgumentException("velocityEngine must not be null");
        }
        this.templateRoot = templateRoot;
        this.velocityEngine = velocityEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        Template template = velocityEngine.getTemplate(templateRoot + modelAndView.getViewName());
        Object model = modelAndView.getModel();
        if (model instanceof Map) {
            Map<?, ?> modelMap = (Map<?, ?>) model;
            VelocityContext context = new VelocityContext(modelMap);
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            return writer.toString();
        } else {
            throw new IllegalArgumentException("modelAndView must be of type java.util.Map");
        }
    }

}