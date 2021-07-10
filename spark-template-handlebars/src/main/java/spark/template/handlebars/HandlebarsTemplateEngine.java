/*
 * Copyright 2014
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
package spark.template.handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.cache.GuavaTemplateCache;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.io.TemplateSource;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.eclipse.jetty.io.RuntimeIOException;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Renders HTML from Route output using
 * https://github.com/jknack/handlebars.java.
 * Defaults to the 'templates' directory under the resource path.
 */
public class HandlebarsTemplateEngine extends TemplateEngine {

    /**
     * Default resource root used if none provided.
     */
    public static final String DEFAULT_RESOURCE_ROOT = "/templates";

    protected Handlebars handlebars;

    /**
     * Constructs a handlebars template engine
     */
    public HandlebarsTemplateEngine() {
        this(DEFAULT_RESOURCE_ROOT);
    }

    /**
     * Constructs a handlebars template engine
     *
     * @param resourceRoot the resource root
     */
    public HandlebarsTemplateEngine(String resourceRoot) {
        this(resourceRoot, null);
    }

    /**
     * Constructs a handlebars template engine
     *
     * @param resourceRoot the resource root
     * @param suffix       templates suffix
     */
    public HandlebarsTemplateEngine(String resourceRoot, String suffix) {
        this(resourceRoot, suffix, null);
    }

    /**
     * Constructs a handlebars template engine
     *
     * @param resourceRoot           the resource root
     * @param suffix                 templates suffix
     * @param cacheDurationInMinutes template cache duration in minutes
     */
    public HandlebarsTemplateEngine(String resourceRoot, String suffix, Integer cacheDurationInMinutes) {
        TemplateLoader templateLoader = new ClassPathTemplateLoader();
        templateLoader.setPrefix(resourceRoot != null ? resourceRoot : DEFAULT_RESOURCE_ROOT);
        templateLoader.setSuffix(suffix);

        handlebars = new Handlebars(templateLoader);

        // Set Guava cache.
        Cache<TemplateSource, Template> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(cacheDurationInMinutes != null ? cacheDurationInMinutes : 10, TimeUnit.MINUTES)
                .maximumSize(1000).build();

        handlebars = handlebars.with(new GuavaTemplateCache(cache));
    }

    @Override
    public String render(ModelAndView modelAndView) {
        String viewName = modelAndView.getViewName();
        try {
            Template template = handlebars.compile(viewName);
            return template.apply(modelAndView.getModel());
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }
}
