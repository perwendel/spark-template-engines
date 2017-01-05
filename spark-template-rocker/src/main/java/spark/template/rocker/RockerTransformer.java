package spark.template.rocker;

import com.fizzed.rocker.runtime.DefaultRockerModel;
import spark.ResponseTransformer;

/**
 */
public class RockerTransformer implements ResponseTransformer {

    /**
     * {@inheritDoc}
     */    
    @Override
    public String render(final Object o) throws Exception {
        return ((DefaultRockerModel) o).render().toString();
    }

}
