package spark.template.rythm.example;

import org.rythmengine.extension.ISourceCodeEnhancer;
import org.rythmengine.template.ITemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleSourceEnhancer implements ISourceCodeEnhancer {

    @Override
    public Map<String, ?> getRenderArgDescriptions() {
        Map<String, Object> m = new HashMap<>();
        m.put("myPojo", "spark.template.rythm.example.Pojo");
        return m;
    }

    @Override
    public void setRenderArgs(ITemplate template) {
        Map<String, Object> implicitVars = new HashMap<>();

        Pojo esePojo = new Pojo();
        esePojo.setName("ESE POJO");

        implicitVars.put( "myPojo" , esePojo );

        template.__setRenderArgs( implicitVars);
    }

    @Override
    public List<String> imports() {
        return Arrays.asList("spark.template.rythm.example.Pojo");
    }

    @Override
    public String sourceCode() {
        return null;
    }
}
