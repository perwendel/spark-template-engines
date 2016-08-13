package spark.template.jinjava;

import java.io.IOException;
import java.util.Map;

import spark.ModelAndView;
import spark.TemplateEngine;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;
import com.hubspot.jinjava.interpret.Context;
import com.hubspot.jinjava.lib.filter.Filter;
import com.hubspot.jinjava.lib.fn.ELFunctionDefinition;
import com.hubspot.jinjava.lib.tag.Tag;
import com.hubspot.jinjava.loader.ResourceLocator;
import com.hubspot.jinjava.loader.ResourceNotFoundException;

/**
 * Renders HTML from Route output using Jinjava.
 * Jinjava {@link JinjavaConfig} and {@link ResourceLocator} can be set with the {@link JinjavaTemplateEngine(JinjavaConfig, ResourceLocator)}
 * constructor. If no {@link JinjavaConfig} or resource root is set, the default path for the template files
 * will be in templates under the resources directory.
 */
public class JinjavaTemplateEngine extends TemplateEngine {

    private Jinjava jinjava;
    private Context context;

    public JinjavaTemplateEngine() {
        this("/templates");
    }

    public JinjavaTemplateEngine(String templateRoot) {
        this(new JinjavaConfig(), templateRoot);
    }

    public JinjavaTemplateEngine(JinjavaConfig jinjavaConfig, String templateRoot) {
        this(jinjavaConfig, (fullName, encoding, interpreter) -> {
            String path = templateRoot;
            if (templateRoot.startsWith("/")) {
                path = templateRoot.substring(1);
            }
            try {
                return Resources.toString(Resources.getResource(path + "/" + fullName), encoding);
            } catch (IllegalArgumentException e) {
                throw new ResourceNotFoundException("Couldn't find resource: " + fullName);
            }
        });
    }

    public JinjavaTemplateEngine(JinjavaConfig jinjavaConfig, ResourceLocator resourceLocator) {
        jinjava = new Jinjava(jinjavaConfig);
        jinjava.setResourceLocator(resourceLocator);
        context = jinjava.getGlobalContext();
    }

    public void registerTag(Tag tag) {
        context.registerTag(tag);
    }

    public void registerFilter(Filter filter) {
        context.registerFilter(filter);
    }

    public void registerFuntion(ELFunctionDefinition function) {
        context.registerFunction(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        String template = "";
        Object model = modelAndView.getModel();
        try {
            template = jinjava.getResourceLocator().getString(modelAndView.getViewName(), Charsets.UTF_8, jinjava.newInterpreter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jinjava.render(template, (Map<String, Object>) model);
    }
}
