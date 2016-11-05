/*
 * Copyright 2013 Per Wendel
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
package spark.template.velocity;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Template Engine based on Apache Velocity.
 */
public class VelocityTemplateEngine extends TemplateEngine {

    private final VelocityEngine velocityEngine;
    private String encoding;

    /**
     * Constructor
     * 
     * @param encoding The encoding the template files use. May be null.
     */
    public VelocityTemplateEngine(String encoding) {
    	this.encoding = encoding;
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty(
                "class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine = new org.apache.velocity.app.VelocityEngine(properties);
    }
    
    /**
     * Constructor
     */
    public VelocityTemplateEngine() {
    	this((String) null);
    }
    
    /**
     * Constructor
     * 
     * @param charset The encoding the template files use, must not be null.
     */
    public VelocityTemplateEngine(Charset charset) {
    	this(charset.name());
    }

    /**
     * Constructor
     *
     * @param velocityEngine The velocity engine, must not be null.
     * @param encoding The encoding the template files use. May be null.
     */
    public VelocityTemplateEngine(VelocityEngine velocityEngine, String encoding) {
        if (velocityEngine == null) {
            throw new IllegalArgumentException("velocityEngine must not be null");
        }
        this.velocityEngine = velocityEngine;
        this.encoding = encoding;
    }
    
    /**
     * Constructor
     *
     * @param velocityEngine The velocity engine, must not be null.
     */
    public VelocityTemplateEngine(VelocityEngine velocityEngine) {
    	this(velocityEngine, (String) null);
    }
    
    /**
     * Constructor
     *
     * @param velocityEngine The velocity engine, must not be null.
     * @param charset The encoding the template files use, must not be null.
     */
    public VelocityTemplateEngine(VelocityEngine velocityEngine, Charset charset) {
    	this(velocityEngine, charset.name());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
    	Template template;
    	if(encoding != null || !encoding.isEmpty()) {
    		template = velocityEngine.getTemplate(modelAndView.getViewName(), encoding);
    	}
    	else {
    		template = velocityEngine.getTemplate(modelAndView.getViewName());
    	}
        Object model = modelAndView.getModel();
        if (model instanceof Map) {
            Map<?, ?> modelMap = (Map<?, ?>) model;
            VelocityContext context = new VelocityContext(modelMap);
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            return writer.toString();
        } else {
            throw new IllegalArgumentException("modelAndView must be of type java.util.Map");
        }
    }

}