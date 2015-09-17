/*
 * Copyright 2015 - Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark.template.thymeleaf;

import java.util.Map;

import org.thymeleaf.context.Context;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Defaults to the 'templates' directory under the resource path
 *
 * @author David Vaillant https://github.com/dvaillant
 */
public class ThymeleafTemplateEngine extends TemplateEngine {

    private org.thymeleaf.TemplateEngine templateEngine;

    /**
     * Constructs a default thymeleaf template engine.
     * Defaults prefix (template directory in resource path) to templates/ and suffix to .html
     */
    public ThymeleafTemplateEngine() {
        this("templates/", ".html");
    }

    /**
     * Constructs a thymeleaf template engine with specified prefix and suffix
     *
     * @param prefix the prefix (template directory in resource path)
     * @param suffix the suffix (e.g. .html)
     */
    public ThymeleafTemplateEngine(String prefix, String suffix) {
        TemplateResolver defaultTemplateResolver = new TemplateResolver();
        defaultTemplateResolver.setTemplateMode("XHTML");

        if (prefix != null) {
            defaultTemplateResolver.setPrefix(prefix);
        } else {
            defaultTemplateResolver.setPrefix("templates/");
        }

        if (suffix != null) {
            defaultTemplateResolver.setSuffix(suffix);
        } else {
            defaultTemplateResolver.setSuffix(".html");
        }

        defaultTemplateResolver.setCacheTTLMs(3600000L);
        defaultTemplateResolver.setResourceResolver(new ClassLoaderResourceResolver());

        initialize(defaultTemplateResolver);
    }

    /**
     * Constructs a thymeleaf template engine with a proprietary initialize
     *
     * @param templateResolver the template resolver.
     */
    public ThymeleafTemplateEngine(TemplateResolver templateResolver) {
        initialize(templateResolver);
    }

    /**
     * Initializes and sets the template resolver
     */
    private final void initialize(TemplateResolver templateResolver) {
        templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String render(ModelAndView modelAndView) {
        Object model = modelAndView.getModel();

        if (model instanceof Map) {
            Map<String, ?> modelMap = (Map<String, ?>) model;
            Context context = new Context();
            context.setVariables(modelMap);
            return templateEngine.process(modelAndView.getViewName(), context);
        } else {
            throw new IllegalArgumentException("modelAndView.getModel() must return a java.util.Map");
        }

    }
}
