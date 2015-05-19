/**
 * Copyright (c) 2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark.template.jade;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

import static spark.Spark.get;

/**
 * Jade template engine example.
 */
public class JadeTemplateExample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Hello World!");

        // The hello.jade template file is in the resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello"), new JadeTemplateEngine());
    }
}
