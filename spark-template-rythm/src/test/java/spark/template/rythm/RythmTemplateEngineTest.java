package spark.template.rythm;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import org.rythmengine.Rythm;
import spark.ModelAndView;
import spark.template.rythm.example.ExampleSourceEnhancer;
import spark.template.rythm.example.Pojo;

import static org.junit.Assert.*;

public class RythmTemplateEngineTest {

    private RythmProperties properties;

    @Before
    public void setup() {
        File f = new File(".");
        String ROOT_DIR = f.getAbsolutePath() + "/src/test/resources/";

        properties = new RythmProperties();
        properties.setTemplateDirectory(ROOT_DIR);
        properties.setTmpDirectory(ROOT_DIR + "/tmp");
        properties.setEngineFileWriteEnabled(true);
        properties.setRythmEngineMode(Rythm.Mode.dev);
    }

    @Test
    public void test_basic_render() {
        String templateVariable = "Hello Rythm!";

        Map<String, Object> model = new HashMap<>();

        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new RythmTemplateEngine(properties).render(new ModelAndView(model, "hello.html"));
        assertEquals(expected, actual);
    }

    @Test
    public void test_double_render() {
        Map<String, Object> model = new HashMap<>();

        String templateVariable = "Hello Double Rythm!";
        model.put("message", templateVariable);

        Pojo pojo = new Pojo();
        pojo.setName("MyName");
        model.put("pojo", pojo);

        String expected = String.format("<h1>%s</h1><h2>%s</h2>", templateVariable, pojo.getName());
        String actual = new RythmTemplateEngine(properties)
            .render(new ModelAndView(model, "twovars.html"))
            .trim();

        assertEquals(expected, actual);
    }

    @Test
    public void test_source_code_enhancer_render() {

        ExampleSourceEnhancer ese = new ExampleSourceEnhancer();
        properties.setRythmCodegenSourceCodeEnhancer(ese);

        Map<String, Object> model = new HashMap<>();

        String templateVariable = "Hello SCE Rythm!";
        model.put("message", templateVariable);

        String expected = String.format("<h1>%s</h1><h2>%s</h2>", templateVariable, "ESE POJO");
        String actual = new RythmTemplateEngine(properties)
            .render(new ModelAndView(model, "sce.html"))
            .trim();

        assertEquals(expected, actual);
    }
}