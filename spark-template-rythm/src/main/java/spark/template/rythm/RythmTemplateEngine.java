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
package spark.template.rythm;

import java.util.*;

import org.rythmengine.Rythm;

import org.rythmengine.RythmEngine;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Template Engine based on Rythm.
 */
public class RythmTemplateEngine extends TemplateEngine {

    private final RythmEngine rythmEngine;

    /**
     * Constructor
     */
    public RythmTemplateEngine(RythmProperties setupProperties) {
        Map<String, Object> rythmEngineProperties = new HashMap<>();

        rythmEngineProperties.put("home.template", setupProperties.getTemplateDirectory());
        rythmEngineProperties.put("home.tmp.dir", setupProperties.getTmpDirectory());

        rythmEngineProperties.put("engine.file_write.enabled", setupProperties.isEngineFileWriteEnabled());

        if (setupProperties.getRythmEngineMode() != null) {
            rythmEngineProperties.put("rythm.engine.mode", setupProperties.getRythmEngineMode().toString());
        }

        if (setupProperties.getRythmCodegenSourceCodeEnhancer() != null) {
            rythmEngineProperties.put("rythm.codegen.source_code_enhancer", setupProperties.getRythmCodegenSourceCodeEnhancer());
        }

        // Need to shutdown Rythm in order to "re-initialize"
        Rythm.shutdown();

        Rythm.init(rythmEngineProperties);

        rythmEngine = Rythm.engine();
    }

    /**
     * Constructor
     *
     * @param rythmEngine The rythm engine, must not be null.
     */
    public RythmTemplateEngine(RythmEngine rythmEngine) {
        if (rythmEngine == null) {
            throw new IllegalArgumentException("rythmEngine must not be null");
        }
        this.rythmEngine = rythmEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        Object model = modelAndView.getModel();

        String template = modelAndView.getViewName();

        return rythmEngine.render(template, model);
    }

}