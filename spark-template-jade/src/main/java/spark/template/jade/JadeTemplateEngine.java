/**
 * Copyright (c) 2015
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark.template.jade;

import java.io.IOException;
import java.util.Map;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.template.JadeTemplate;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.jade.loader.SparkClasspathTemplateLoader;

/**
 * Template engine to render HTML from jade templates.
 */
public class JadeTemplateEngine extends TemplateEngine {

    private JadeConfiguration configuration;

    /**
     * Construct a jade template engine with 'templates' as root directory.
     */
    public JadeTemplateEngine() {
        this("templates");
    }

    /**
     * Construct a jade template engine with specified root.
     *
     * @param templateRoot the template root directory to use
     */
    public JadeTemplateEngine(String templateRoot) {
        configuration = new JadeConfiguration();
        configuration.setTemplateLoader(new SparkClasspathTemplateLoader(templateRoot));
    }

    /**
     * Construct a jade template engine from a raw JadeConfiguration
     *
     * @param configuration the raw JadeConfiguration to use
     */
    public JadeTemplateEngine(JadeConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Access to the internal JadeConfiguration
     *
     * @return The JadeConfiguration used by this engine
     */
    public JadeConfiguration configuration() {
        return configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public String render(ModelAndView modelAndView) {
        try {
            JadeTemplate template = configuration.getTemplate(modelAndView.getViewName());
            return configuration.renderTemplate(template, (Map<String, Object>) modelAndView.getModel());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
