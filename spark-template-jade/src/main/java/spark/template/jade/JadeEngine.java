package spark.template.jade;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.template.ClasspathTemplateLoader;
import de.neuland.jade4j.template.FileTemplateLoader;
import de.neuland.jade4j.template.JadeTemplate;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.jade.loader.SparkClasspathLoader;

import java.io.IOException;
import java.util.Map;

public class JadeEngine extends TemplateEngine {

    private JadeConfiguration configuration;

    public JadeEngine() {
        configuration = new JadeConfiguration();
        configuration.setTemplateLoader(new SparkClasspathLoader("templates/"));
    }

    public JadeEngine(String contentRoot) {
        configuration = new JadeConfiguration();
        configuration.setTemplateLoader(new SparkClasspathLoader(contentRoot));
    }

    public JadeEngine(JadeConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String render(ModelAndView modelAndView) {
        try {
            JadeTemplate template = configuration.getTemplate(modelAndView.getViewName());

            return configuration.renderTemplate(template, (Map<String, Object>) modelAndView.getModel());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
