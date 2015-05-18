package spark.template.jade.loader;

import de.neuland.jade4j.template.ClasspathTemplateLoader;
import de.neuland.jade4j.template.TemplateLoader;

import java.io.IOException;
import java.io.Reader;

public class SparkClasspathLoader extends ClasspathTemplateLoader {

    private String templateRoot;

    public SparkClasspathLoader(String templateRoot) {
        this.templateRoot = templateRoot;
    }

    @Override
    public Reader getReader(String name) throws IOException {
        return super.getReader(templateRoot + name);
    }
}
