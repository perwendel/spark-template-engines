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
package spark.template.closure;

import com.google.common.base.Preconditions;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.util.Map;

/**
 * Google's Closure template engine for sparkjava.
 * See: https://developers.google.com/closure/templates/
 *
 * @author Prasanna Venkat https://github.com/prasannasv
 */
public class ClosureTemplateEngine extends TemplateEngine {

    private final SoyTofu soyTofu;

    /**
     * Constructs a closure template engine with the given soy file paths.
     *
     * @param soyFilePaths one or more paths to .soy files relative to /templates in the resources folder.
     * @throws IllegalArgumentException if the input is null.
     */
    public ClosureTemplateEngine(final String... soyFilePaths) {
        Preconditions.checkNotNull(soyFilePaths, "at least one path to soy file must be specified");

        SoyFileSet.Builder sfsBuilder = SoyFileSet.builder();
        for (final String soyFilePath : soyFilePaths) {
            sfsBuilder = sfsBuilder.add(ClosureTemplateEngine.class.getResource("/templates/" + soyFilePath));
        }

        soyTofu = sfsBuilder.build().compileToTofu();
    }

    /**
     * Constructs a closure template engine with a compiled tofu
     *
     * @param soyTofu the compiled SoyTofu instance
     */
    public ClosureTemplateEngine(final SoyTofu soyTofu) {
        this.soyTofu = soyTofu;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String render(final ModelAndView modelAndView) {
        final String templateName = modelAndView.getViewName();

        if (modelAndView.getModel() instanceof Map) {
            final Map<String, ?> soyMapData = (Map<String, ?>) modelAndView.getModel();

            return soyTofu.newRenderer(templateName).setData(soyMapData).render();
        }

        throw new IllegalArgumentException("Expecting the model to be an instance of Map<String, ?>");
    }
}
