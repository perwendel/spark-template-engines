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
package spark.template.jetbrick;

import java.io.StringWriter;
import java.util.Map;

import jetbrick.template.JetContext;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * JetTemplateEngine  based on Jetbrick-template
 * See https://github.com/subchen/jetbrick-template-2x
 */
public class JetbrickTemplateEngine extends TemplateEngine {

    private JetEngine jetEngine;

    public JetbrickTemplateEngine() {
        jetEngine = JetEngine.create();
    }

    /**
     * Constructs a JetBrick template engine.
     *
     * @param jetEngine the JetEngine
     */
    public JetbrickTemplateEngine(JetEngine jetEngine) {
        if (null == jetEngine) {
            throw new IllegalArgumentException("jetEngine must not be null");
        }
        this.jetEngine = jetEngine;
    }

    @Override
    public String render(ModelAndView modelAndView) {
        JetTemplate template = jetEngine.getTemplate(modelAndView.getViewName());
        Object model = modelAndView.getModel();
        if (model instanceof Map) {
            Map<String, Object> modelMap = (Map<String, Object>) model;
            JetContext context = new JetContext(modelMap.size());
            context.putAll(modelMap);
            StringWriter writer = new StringWriter();
            template.render(context, writer);
            return writer.toString();
        } else {
            throw new IllegalArgumentException("modelAndView.getModel() must return a java.util.Map");
        }
    }

}
