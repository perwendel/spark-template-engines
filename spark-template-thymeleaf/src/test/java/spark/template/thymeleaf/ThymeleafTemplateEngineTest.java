package spark.template.thymeleaf;

import java.util.HashMap;
import java.util.Map;
import java.util.Locale;

import org.junit.Test;

import spark.ModelAndView;

import static org.junit.Assert.*;

public class ThymeleafTemplateEngineTest {

    @Test
    public void test_render() throws Exception {
        String templateVariable = "Hello Thymeleaf!";
        Map<String, Object> model = new HashMap<>();
        model.put("message", templateVariable);
        String expected = String.format("<h1>%s</h1>", templateVariable);
        String actual = new ThymeleafTemplateEngine().render(new ModelAndView(model, "hello"));
        assertEquals(expected, actual);
    }

    @Test
    public void test_Locale_unknown_render() throws Exception {
        String templateVariable = "Welcome unknown locale";
        Map<String, Object> model = new HashMap<>();

        String expected = String.format("<p>%s</p>", templateVariable);
        System.out.println(templateVariable);
        // read from home.properties - default location if no language found
        String actual = new ThymeleafTemplateEngine().render(new ModelAndView(model, "home"), new Locale("unknown"));
        assertEquals(expected, actual);
    }

    @Test
    public void test_Locale_hr_render() throws Exception {
        String templateVariable = "Dobrodošli";
        Map<String, Object> model = new HashMap<>();

        String expected = String.format("<p>%s</p>", templateVariable);
        String actual = new ThymeleafTemplateEngine().render(new ModelAndView(model, "home"), new Locale("hr"));
        assertEquals(expected, actual);
    }

    @Test
    public void test_Locale_hr_HR_render() throws Exception {
        String templateVariable = "Dobrodošli";
        Map<String, Object> model = new HashMap<>();

        String expected = String.format("<p>%s</p>", templateVariable);
        String actual = new ThymeleafTemplateEngine().render(new ModelAndView(model, "home"), new Locale("hr", "HR"));
        assertEquals(expected, actual);
    }

    @Test
    public void test_Locale_de_render() throws Exception {
        String templateVariable = "Willkommen";
        Map<String, Object> model = new HashMap<>();

        String expected = String.format("<p>%s</p>", templateVariable);
        System.out.println(templateVariable);
        String actual = new ThymeleafTemplateEngine().render(new ModelAndView(model, "home"), new Locale("de"));
        assertEquals(expected, actual);
    }

    @Test
    public void test_Locale_zh_render() throws Exception {
        String templateVariable = "结果";
        Map<String, Object> model = new HashMap<>();

        String expected = String.format("<p>%s</p>", templateVariable);
        System.out.println(templateVariable);
        String actual = new ThymeleafTemplateEngine().render(new ModelAndView(model, "home"), new Locale("zh"));
        assertEquals(expected, actual);
    }

}