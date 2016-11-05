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

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

/**
 * Handlebars template engine example
 *
 * @author Sam Pullara https://github.com/spullara
 */
public class HandlebarsTemplateExample {

    public static void main(String[] args) {

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Handlebars!");
            return new ModelAndView(model, "hello.hbs"); // located in resources/templates
        }, new HandlebarsTemplateEngine());

    }

}
