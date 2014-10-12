package spark.template.pebble;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * Template Engine based on Pebble.
 *
 * @author Nikki
 */
public class PebbleTemplateEngine extends TemplateEngine {

	/**
	 * The Pebble Engine instance.
	 */
	private final PebbleEngine engine;

	/**
	 * Construct a new template engine using pebble with a default engine.
	 */
	public PebbleTemplateEngine() {
		Loader loader = new ClasspathLoader();
		loader.setPrefix(PebbleTemplateEngine.class.getPackage().getName().replace('.', '/'));
		this.engine = new PebbleEngine(loader);
	}

	/**
	 * Construct a new template engine using pebble with an engine using a special loader.
	 */
	public PebbleTemplateEngine(Loader loader) {
		this.engine = new PebbleEngine(loader);
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
		try {
			StringWriter writer = new StringWriter();

			PebbleTemplate template = engine.getTemplate(modelAndView.getViewName());
			template.evaluate(writer, (Map<String, Object>) modelAndView.getModel());

			return writer.toString();
		} catch (PebbleException | IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
