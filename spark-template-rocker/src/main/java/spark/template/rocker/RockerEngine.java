package spark.template.rocker;

import java.util.Map;

import com.fizzed.rocker.Rocker;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 */
public class RockerEngine extends TemplateEngine {

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
	return Rocker.template(modelAndView.getViewName())
	    .bind((Map<String,Object>)modelAndView.getModel())
	    .render()
	    .toString();
    }

}
