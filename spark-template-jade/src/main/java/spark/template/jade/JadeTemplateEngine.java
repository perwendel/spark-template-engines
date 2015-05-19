/**
 * Copyright (c) 2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark.template.jade;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.template.JadeTemplate;

import java.io.IOException;
import java.util.Map;

import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.jade.loader.SparkClasspathLoader;

/**
 * Template engine to render HTML from jade templates.
 */
public class JadeTemplateEngine extends TemplateEngine {

    private JadeConfiguration configuration;

    /**
     * Construct a jade template engine defaulting to the 'templates' directory
     * under the resource path.
     */
    public JadeTemplateEngine() {
        this("templates");
    }

    /**
     * Construct a jade template engine.
     *
     * @param templateRoot the template root directory to use
     */
    public JadeTemplateEngine(String templateRoot) {
        configuration = new JadeConfiguration();
        configuration.setTemplateLoader(new SparkClasspathLoader(templateRoot));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public String render(ModelAndView modelAndView) {
        try {
            JadeTemplate template = configuration.getTemplate(modelAndView.getViewName());
            return configuration.renderTemplate(template,
                    (Map<String, Object>) modelAndView.getModel());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
