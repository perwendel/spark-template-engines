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
 * Defaults to the 'templates' directory under the resource path.
 *
 * @author David Vaillant https://github.com/dvaillant
 */
public class ThymeleafTemplateEngine extends TemplateEngine {

    private org.thymeleaf.TemplateEngine templateEngine;

    /**
     * Constructs a default thymeleaf template engine
     */
    public ThymeleafTemplateEngine() {

        TemplateResolver defaultTemplateResolver =
                new TemplateResolver();
        defaultTemplateResolver.setTemplateMode("XHTML");
        defaultTemplateResolver.setPrefix("templates/");
        defaultTemplateResolver.setSuffix(".html");
        defaultTemplateResolver.setCacheTTLMs(3600000L);

        defaultTemplateResolver.setResourceResolver(new ClassLoaderResourceResolver());

        templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(defaultTemplateResolver);
    }

    /**
     * Constructs a thymeleaf template engine
     *
     * @param templateResolver
     */
    public ThymeleafTemplateEngine(TemplateResolver templateResolver) {
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
