package spark.template.thymeleaf;

import java.util.Map;

import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Defaults to the 'templates' directory under the resource path
 *
 * @author David Vaillant https://github.com/dvaillant
 */
public class ThymeleafTemplateEngine extends TemplateEngine {

    private final org.thymeleaf.TemplateEngine templateEngine;

    /**
     * Constructs a default thymeleaf template engine.
     * Defaults prefix (template directory in resource path) to templates/ and suffix to .html
     */
    public ThymeleafTemplateEngine() {
        this("templates/");
    }

    /**
     * Constructs a thymeleaf template engine with specified prefix and suffix
     *
     * @param templateRoot the prefix (template directory in resource path)
     */
    public ThymeleafTemplateEngine(String templateRoot) {
        this(new ClassLoaderTemplateResolver() {{
            setPrefix(templateRoot);
        }});
    }

    /**
     * Constructs a thymeleaf template engine with a proprietary initialize
     *
     * @param templateResolver the template resolver.
     */
    public ThymeleafTemplateEngine(ITemplateResolver templateResolver) {
        this.templateEngine = new org.thymeleaf.TemplateEngine() {{
            setTemplateResolver(templateResolver);
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public String render(ModelAndView modelAndView) {
        Object model = modelAndView.getModel();

        if (model instanceof Map) {
            Map<String, Object> modelMap = (Map<String, Object>) model;
            Context context = new Context();
            context.setVariables(modelMap);
            return templateEngine.process(modelAndView.getViewName(), context);
        } else {
            throw new IllegalArgumentException("modelAndView.getModel() must return a java.util.Map");
        }
    }
}
