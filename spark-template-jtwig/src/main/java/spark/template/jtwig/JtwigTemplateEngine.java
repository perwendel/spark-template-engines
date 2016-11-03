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

import java.util.Map;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Defaults to the 'templates' directory under the resource path.
 *
 * @author Sam Pullara https://github.com/spullara
 */
public class JtwigTemplateEngine extends TemplateEngine {

	/**
	 * Templates directory in resources
	 */
	private String templatesDirectory;

	/**
	 * Setup default templates directory
	 */
	public JtwigTemplateEngine() {
		this.templatesDirectory = "templates";
	}

	/**
	 * Setup custom templates directory
	 * 
	 * @param customTemplatesDirectory
	 *            The custom templates directory
	 */
	public JtwigTemplateEngine(String customTemplatesDirectory) {
		this.templatesDirectory = customTemplatesDirectory;
	}

	@Override
	public String render(ModelAndView modelAndView) {
		String viewName = templatesDirectory + "/" + modelAndView.getViewName();
		JtwigTemplate template = JtwigTemplate.classpathTemplate(viewName);
		JtwigModel model = JtwigModel.newModel((Map) modelAndView.getModel());
		return template.render(model);
	}
}
