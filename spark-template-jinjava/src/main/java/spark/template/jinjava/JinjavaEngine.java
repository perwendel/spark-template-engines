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
import com.hubspot.jinjava.loader.FileLocator;
import com.hubspot.jinjava.loader.ResourceLocator;

public class JinjavaEngine extends TemplateEngine {

    private Jinjava jinjava;
    private Context context;

    public JinjavaEngine() {
        this(new JinjavaConfig());
    }

    public JinjavaEngine(JinjavaConfig jinjavaConfig) {
        this(jinjavaConfig, new FileLocator());
    }

    public JinjavaEngine(JinjavaConfig jinjavaConfig, ResourceLocator resourceLocator) {
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

    public void registerFunction(ELFunctionDefinition function) {
        context.registerFunction(function);
    }

    @Override
    public String render(ModelAndView modelAndView) {
        Object model = modelAndView.getModel();
        if (model instanceof Map) {
            String template = null;
            try {
                template = Resources.toString(Resources.getResource(modelAndView.getViewName()), Charsets.UTF_8);
            } catch (IOException ignored) {
            }
            return jinjava.render(template, (Map<String, Object>) model);
        } else {
            throw new IllegalArgumentException("modelAndView.getModel() must return a java.util.Map");
        }
    }
}
