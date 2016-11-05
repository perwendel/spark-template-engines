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
package spark.template.jtwig;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

/**
 * Jtwig template engine example
 *
 * @author Rafael Andrade de Oliveira https://github.com/eurafa
 */
public class JtwigTemplateExample {

    public static void main(String[] args) {

        get("/hello", (rq, rs) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Jtwig!");
            return new ModelAndView(model, "hello.twig"); // hello.twig file is in resources/templates directory
        }, new JtwigTemplateEngine());

    }

}
