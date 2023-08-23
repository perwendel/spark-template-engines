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
package spark.template.pebble;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.error.PebbleException;
import io.pebbletemplates.pebble.loader.Loader;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import spark.ModelAndView;
import spark.TemplateEngine;


/**
 * Template Engine based on Pebble.
 *
 * @author Nikki
 */
public class PebbleTemplateEngine extends TemplateEngine {

    /**
     * The Pebble Engine instance.
     */
    private final PebbleEngine engine;

    /**
     * Construct a new template engine using pebble with a default engine.
     */
    public PebbleTemplateEngine() {
        this.engine = new PebbleEngine.Builder().build();
    }

    /**
     * Construct a new template engine using pebble with an engine using a special loader.
     */
    public PebbleTemplateEngine(Loader loader) {
        this.engine = new PebbleEngine.Builder().loader(loader).build();
    }

    /**
     * Construct a new template engine using pebble with a specified engine.
     *
     * @param engine The pebble template engine.
     */
    public PebbleTemplateEngine(PebbleEngine engine) {
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public String render(ModelAndView modelAndView) {
        Object model = modelAndView.getModel();

        if (model == null || model instanceof Map) {
            try {
                StringWriter writer = new StringWriter();

                PebbleTemplate template = engine.getTemplate(modelAndView.getViewName());
                if (model == null) {
                    template.evaluate(writer);
                } else {
                    template.evaluate(writer, (Map<String, Object>) modelAndView.getModel());
                }

                return writer.toString();
            } catch (PebbleException | IOException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new IllegalArgumentException("Invalid model, model must be instance of Map.");
        }
    }
}
