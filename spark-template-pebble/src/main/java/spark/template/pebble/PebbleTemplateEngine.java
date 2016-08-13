package spark.template.pebble;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import spark.ModelAndView;
import spark.TemplateEngine;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

public class PebbleTemplateEngine extends TemplateEngine {

	/**
     * The {@link PebbleEngine} instance.
     */
    private final PebbleEngine engine;
    private String templateRoot = "/templates";

	/**
	 * Construct a new template engine using pebble with a default engine.
	 */
	public PebbleTemplateEngine() {
        this("./templates/");
    }

    public PebbleTemplateEngine(String templateRoot) {
        this.templateRoot = templateRoot;
        this.engine = new PebbleEngine.Builder().build();
    }

	/**
	 * Construct a new template engine using pebble with an engine using a special loader.
     *
     * @param loader The {@link PebbleEngine} {@link Loader}
     */
    public PebbleTemplateEngine(Loader loader) {
		this.engine = new PebbleEngine.Builder().loader(loader).build();
	}

	/**
	 * Construct a new template engine using pebble with a specified engine.
	 *
	 * @param engine The pebble template engine.
	 */
	public PebbleTemplateEngine(PebbleEngine engine) {
		this.engine = engine;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String render(ModelAndView modelAndView) {
		Object model = modelAndView.getModel();

		if (model == null || model instanceof Map) {
			try {
				StringWriter writer = new StringWriter();

                PebbleTemplate template = engine.getTemplate(templateRoot + modelAndView.getViewName());
                if (model == null) {
                    template.evaluate(writer);
				} else {
					template.evaluate(writer, (Map<String, Object>) modelAndView.getModel());
				}

				return writer.toString();
			} catch (PebbleException | IOException e) {
				throw new IllegalArgumentException(e);
			}
		} else {
			throw new IllegalArgumentException("Invalid model, model must be instance of Map.");
		}
	}
}
