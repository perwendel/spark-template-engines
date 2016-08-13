package spark.template.handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.eclipse.jetty.io.RuntimeIOException;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.io.IOException;

/**
 * Renders HTML from Route output using Handlebars.
 * Handlebars {@link TemplateLoader} can be set with the {@link HandlebarsTemplateEngine (TemplateLoader)}
 * constructor. If no {@link TemplateLoader} or resource root is set, the default path for the template files
 * will be in templates under the resources directory.
 */
public class HandlebarsTemplateEngine extends TemplateEngine {

    /**
     * The {@link Handlebars}
     */
    private Handlebars handlebars;

    /**
     * Constructs a handlebars template engine
     */
    public HandlebarsTemplateEngine() {
        this("/templates");
    }

    /**
     * Constructs a handlebars template engine
     *
     * @param resourceRoot the resource root
     */
    public HandlebarsTemplateEngine(String resourceRoot) {
        this(new ClassPathTemplateLoader() {{
            setPrefix(resourceRoot);
            setSuffix("");
        }});
    }

    /**
     * Constructs the Handlebars with a {@link TemplateLoader}
     *
     * @param templateLoader a template loader
     */
    public HandlebarsTemplateEngine(TemplateLoader templateLoader) {
        this.handlebars = new Handlebars(templateLoader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        String viewName = modelAndView.getViewName();
        try {
            Template template = this.handlebars.compile(viewName);
            return template.apply(modelAndView.getModel());
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }
}
