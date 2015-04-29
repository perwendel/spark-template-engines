package spark.template.water;

import org.watertemplate.Template;
import spark.Request;
import spark.ResponseTransformer;

import java.util.Locale;

public class WaterTemplateEngine implements ResponseTransformer {

    private static final WaterTemplateEngine waterTemplateEngine = new WaterTemplateEngine();

    @Override
    public String render(final Object o) throws Exception {
        return ((Render) o).render();
    }

    public static Render render(final Template template, final Request request) {
        return new Render(template, request.raw().getLocale());
    }

    public static WaterTemplateEngine waterEngine() {
        return waterTemplateEngine;
    }

    //

    private static class Render {
        private final Locale locale;
        private final Template template;

        public Render(final Template template, final Locale locale) {
            this.template = template;
            this.locale = locale;
        }

        public String render() {
            return template.render(locale);
        }
    }
}
