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

import com.fizzed.rocker.BindableRockerModel;
import com.fizzed.rocker.Rocker;
import com.fizzed.rocker.runtime.RockerRuntime;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test rendering with Rocker
 */
public class RockerTemplateTest extends Assert {

    @Test
    public void rendering() {
        RockerRuntime.getInstance().setReloading(true);
        BindableRockerModel template = Rocker.template(
                "rocker/views/ExampleTemplate.rocker.html", "Hello World!");
        RockerTemplateModel templateModel = new RockerTemplateModel(template);
        String actual = RockerTemplateEngine.getInstance().render(templateModel);
        String expected = "<h1>Hello World!</h1>\n";
        assertEquals(expected, actual);
    }
}
