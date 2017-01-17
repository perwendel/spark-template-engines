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

public class RockerTemplateModel extends ModelAndView {

    /**
     * Constructs an instance with RockerModel template
     *
     * @param template the RockerModel template
     */
    public RockerTemplateModel(RockerModel template) {
        // No sense of template name as the template is a Rocker generated model
        super(template, null);
    }
}
