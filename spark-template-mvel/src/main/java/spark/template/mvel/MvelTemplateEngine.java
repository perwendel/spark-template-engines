package spark.template.mvel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;

import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Template Engine based on <a href="https://github.com/mvel/mvel">MVEL</a>.
 *
 * @author DevNG https://github.com/devng
 */
public class MvelTemplateEngine extends TemplateEngine {

    private static final String DEFAULT_PREFIX = "templates/";

    // lets do some caching of expressions to see if we cant go a bit faster
    private final ConcurrentMap<String, CompiledTemplate> templateCache;

    private final String prefix;

    public MvelTemplateEngine() {
        this(DEFAULT_PREFIX);
    }

    public MvelTemplateEngine(final String prefix) {
        this.templateCache = new ConcurrentHashMap<>();
        this.prefix = prefix;
    }

    @Override
    public String render(final ModelAndView modelAndView) {
        final CompiledTemplate compiledTemplate = loadTemplate(modelAndView.getViewName());
        return (String) TemplateRuntime.execute(compiledTemplate, modelAndView.getModel());
    }

    public CompiledTemplate loadTemplate(String resourceName) {
        final String fullPath = prefix + resourceName;
        CompiledTemplate compiledTemplate = templateCache.get(fullPath);
        if (compiledTemplate == null) {
            // use the Stupid scanner trick in order not to rely on external libs
            // https://community.oracle.com/blogs/pat/2004/10/23/stupid-scanner-tricks
            compiledTemplate = TemplateCompiler
                    .compileTemplate(Thread.currentThread().getContextClassLoader().getResourceAsStream(fullPath));
            templateCache.putIfAbsent(fullPath, compiledTemplate);
        }
        return compiledTemplate;
    }
}
