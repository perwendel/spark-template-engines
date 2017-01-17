package spark.template.rocker;

import com.fizzed.rocker.RockerModel;
import spark.ModelAndView;
import spark.TemplateEngine;

public class RockerTemplateEngine extends TemplateEngine {

    private static final RockerTemplateEngine INSTANCE = new RockerTemplateEngine();

    private RockerTemplateEngine() {}

    @Override
    public String render(ModelAndView modelAndView) {
        return ((RockerModel) modelAndView.getModel()).render().toString();
    }

    public static TemplateEngine getInstance() {
        return INSTANCE;
    }
}
