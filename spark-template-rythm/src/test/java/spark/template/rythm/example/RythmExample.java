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
package spark.template.rythm.example;

import java.util.HashMap;
import java.util.Map;

import org.rythmengine.Rythm;
import spark.ModelAndView;
import spark.template.rythm.RythmProperties;
import spark.template.rythm.RythmTemplateEngine;

import static spark.Spark.get;

/**
 * VelocityTemplateRoute example.
 */
public final class RythmExample {

    public static void main(String[] args) {
        String ROOT_DIR = "/Users/jeff/dev/github/spark-template-engines/spark-template-rythm/src/test/resources/";

        RythmProperties properties = new RythmProperties();
        properties.setTemplateDirectory(ROOT_DIR);
        properties.setTmpDirectory(ROOT_DIR + "/tmp");
        properties.setEngineFileWriteEnabled(true);
        properties.setRythmEngineMode(Rythm.Mode.dev);

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Rythm");
            return new ModelAndView(model, "hello.html"); // located in the resources directory
        }, new RythmTemplateEngine(properties));

    }

}
