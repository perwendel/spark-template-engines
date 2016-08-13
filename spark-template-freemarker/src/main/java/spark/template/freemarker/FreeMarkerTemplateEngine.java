package spark.template.freemarker;

import java.io.IOException;
import java.io.StringWriter;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Renders HTML from Route output using FreeMarker.
 * FreeMarker {@link Configuration} can be set with the {@link FreeMarkerTemplateEngine (Configuration)}
 * constructor. If no {@link Configuration} or resource root is set, the default path for the template files
 * will be in templates under the resources directory.
 */
public class FreeMarkerTemplateEngine extends TemplateEngine {

    /**
     * The FreeMarker {@link Configuration} instance
     */
    private Configuration configuration;

    /**
     * Constructs the FreeMarkerTemplateEngine
     */
    public FreeMarkerTemplateEngine() {
        this("/templates");
    }

    /**
     * Constructs the FreeMarkerTemplateEngine with resource root
     *
     * @param resourceRoot the root of resources
     */
    public FreeMarkerTemplateEngine(String resourceRoot) {
        this(new Configuration(Configuration.VERSION_2_3_23) {{
            setTemplateLoader(new ClassTemplateLoader(ClassLoader.getSystemClassLoader(), resourceRoot));
        }});
    }

    /**
     * Constructs the FreeMarkerTemplateEngine with a {@link Configuration}
     *
     * @param configuration The Freemarker {@link Configuration}
     */
    public FreeMarkerTemplateEngine(Configuration configuration) {
        this.configuration = configuration;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        try {
            StringWriter stringWriter = new StringWriter();
            Template template = configuration.getTemplate(modelAndView.getViewName());
            template.process(modelAndView.getModel(), stringWriter);

            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
