/*
 * Copyright 2016 - Victory
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
package spark.template.rocker;

import com.fizzed.rocker.RockerModel;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Render Rocker templates
 */
public class RockerTemplateEngine extends TemplateEngine {

    /**
     * Singleton Instance
     */
    private static final RockerTemplateEngine INSTANCE = new RockerTemplateEngine();

    /**
     * Use getInstance() instead
     */
    private RockerTemplateEngine() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        return ((RockerModel) modelAndView.getModel()).render().toString();
    }

    /**
     * Get an instance of the engine
     * @return instance
     */
    public static TemplateEngine getInstance() {
        return INSTANCE;
    }
}
