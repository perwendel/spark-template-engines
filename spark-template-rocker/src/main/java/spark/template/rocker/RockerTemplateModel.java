package spark.template.rocker;

import com.fizzed.rocker.RockerModel;
import spark.ModelAndView;

public class RockerTemplateModel extends ModelAndView {

    /**
     * Constructs an instance with the template
     *
     * @param template the Rocker template
     */
    public RockerTemplateModel(RockerModel template) {
        // No sense of template name as the template is a Rocker generated model
        super(template, null);
    }
}
