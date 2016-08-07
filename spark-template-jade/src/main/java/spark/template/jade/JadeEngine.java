package spark.template.jade;

import java.io.IOException;
import java.util.Map;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.template.ClasspathTemplateLoader;
import de.neuland.jade4j.template.JadeTemplate;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Template engine to render HTML from jade templates.
 */
public class JadeEngine extends TemplateEngine {

    /**
     * The {@link JadeConfiguration} instance
     */
    private final JadeConfiguration configuration;

    /**
     * The root path for the templates
     */
    private final String templateRoot;

    /**
     * Construct a Jade Template Engine
     */
    public JadeEngine() {
        this("/templates");
    }

    /**
     * Construct a Jade Template Engine
     *
     * @param templateRoot The template root directory to use
     */
    public JadeEngine(String templateRoot) {
        this(new JadeConfiguration() {{
            setTemplateLoader(new ClasspathTemplateLoader());
        }}, templateRoot);
    }

    /**
     * Construct a Jade Template Engine from {@link JadeConfiguration}
     *
     * @param configuration The {@link JadeConfiguration} to use
     */
    public JadeEngine(JadeConfiguration configuration, String templateRoot) {
        this.configuration = configuration;
        if (templateRoot.startsWith("/")) {
            templateRoot = templateRoot.substring(1);
        }
        this.templateRoot = templateRoot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        try {
            JadeTemplate template = configuration.getTemplate(templateRoot + "/" + modelAndView.getViewName());
            return configuration.renderTemplate(template, (Map<String, Object>) modelAndView.getModel());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
