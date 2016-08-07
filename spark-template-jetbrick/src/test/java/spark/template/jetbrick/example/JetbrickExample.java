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
package spark.template.jetbrick.example;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.jetbrick.JetbrickTemplateEngine;

import static spark.Spark.get;

public class JetbrickExample {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello from Jetbrick!");

            return new ModelAndView(model, "hello.jetx");
        }, new JetbrickTemplateEngine());

    }

}
