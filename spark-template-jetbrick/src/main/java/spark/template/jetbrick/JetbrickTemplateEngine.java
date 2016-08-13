package spark.template.jetbrick;

import jetbrick.template.JetContext;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * Renders HTML from Route output using Jetbrick.
 * Jetbrick {@link Properties} can be set with the {@link JetbrickTemplateEngine(Properties, String)}
 * constructor. If no {@link Properties} or resource root is set, the default path for the template files
 * will be in templates under the resources directory.
 */
public class JetbrickTemplateEngine extends TemplateEngine {

    /**
     * The Jetbrick {@link JetEngine} instance
     */
    private final JetEngine jetEngine;

    /**
     * The root path for the templates
     */
    private final String templateRoot;

    /**
     * Constructs the JetbrickTemplateEngine
     */
    public JetbrickTemplateEngine() {
        this("/templates");
    }

    /**
     * Constructs the JetbrickTempleEngine with resource root
     *
     * @param templateRoot the root of resources
     */
    public JetbrickTemplateEngine(String templateRoot) {
        this(new Properties(), templateRoot);
    }

    /**
     * Constructs a JetBrickTemplate engine.
     *
     * @param properties  The {@link Properties} for the JetEngine
     * @param templateRoot The root path for the templates
     */
    public JetbrickTemplateEngine(Properties properties, String templateRoot) {
        this.jetEngine = JetEngine.create(properties);
        this.templateRoot = templateRoot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        JetTemplate template = jetEngine.getTemplate(templateRoot + "/" + modelAndView.getViewName());
        Object model = modelAndView.getModel();
        if (model instanceof Map) {
            Map<String, Object> modelMap = (Map<String, Object>) model;
            JetContext context = new JetContext(modelMap.size());
            context.putAll(modelMap);
            StringWriter writer = new StringWriter();
            template.render(context, writer);
            return writer.toString();
        } else {
            throw new IllegalArgumentException("Model must be an instance of java.util.Map");
        }
    }

}
