package spark.template.water;

import java.util.Map;

import org.watertemplate.Template;

import spark.ModelAndView;
import spark.TemplateEngine;

public class WaterTemplateEngine extends TemplateEngine {

    private String templateRoot;

    public WaterTemplateEngine() {
        this("");
    }

    public WaterTemplateEngine(String templateRoot) {
        this.templateRoot = templateRoot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        Template template = new Template() {
            {
                Map<String, Object> model = (Map<String, Object>) modelAndView.getModel();
                for (Map.Entry e : model.entrySet()) {
                    add((String) e.getKey(), (String) e.getValue());
                }
        }

            @Override
            protected String getFilePath() {
                return templateRoot + modelAndView.getViewName();
            }
        };
        return template.render();
    }

}
