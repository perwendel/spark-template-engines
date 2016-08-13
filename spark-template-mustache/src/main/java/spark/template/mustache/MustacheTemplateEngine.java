package spark.template.mustache;

import java.io.IOException;
import java.io.StringWriter;

import org.eclipse.jetty.io.RuntimeIOException;

import spark.ModelAndView;
import spark.TemplateEngine;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

/**
 * Renders HTML from Route output using Mustache.
 * Mustache {@link MustacheFactory} can be set with the {@link MustacheTemplateEngine(MustacheFactory)}
 * constructor. If no {@link MustacheFactory} or resource root is set, the default path for the template files
 * will be in templates under the resources directory.
 */
public class MustacheTemplateEngine extends TemplateEngine {

    /**
     * The {@link MustacheFactory} instance
     */
    private MustacheFactory mustacheFactory;

    /**
     * Constructs a mustache template engine
     */
    public MustacheTemplateEngine() {
        this("/templates");
    }

    /**
     * Constructs a mustache template engine
     *
     * @param resourceRoot the resource root
     */
    public MustacheTemplateEngine(String resourceRoot) {
        if (resourceRoot.startsWith("/")) {
            resourceRoot = resourceRoot.substring(1);
        }
        mustacheFactory = new DefaultMustacheFactory(resourceRoot);
    }

    /**
     * Constructs a mustache template engine
     *
     * @param mustacheFactory the mustache factory
     */
    public MustacheTemplateEngine(MustacheFactory mustacheFactory) {
        this.mustacheFactory = mustacheFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        String viewName = modelAndView.getViewName();
        Mustache mustache = mustacheFactory.compile(viewName);
        StringWriter stringWriter = new StringWriter();
        try {
            mustache.execute(stringWriter, modelAndView.getModel()).close();
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
        return stringWriter.toString();
    }

}
