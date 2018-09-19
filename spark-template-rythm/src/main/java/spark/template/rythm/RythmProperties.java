package spark.template.rythm;

import org.rythmengine.Rythm;
import org.rythmengine.extension.ISourceCodeEnhancer;

public class RythmProperties {
    private String templateDirectory;
    private String tmpDirectory;
    private boolean engineFileWriteEnabled;
    private Rythm.Mode rythmEngineMode;
    private ISourceCodeEnhancer rythmCodegenSourceCodeEnhancer;

    public String getTemplateDirectory() {
        return templateDirectory;
    }

    public void setTemplateDirectory(String templateDirectory) {
        this.templateDirectory = templateDirectory;
    }

    public String getTmpDirectory() {
        return tmpDirectory;
    }

    public void setTmpDirectory(String tmpDirectory) {
        this.tmpDirectory = tmpDirectory;
    }

    public boolean isEngineFileWriteEnabled() {
        return engineFileWriteEnabled;
    }

    public void setEngineFileWriteEnabled(boolean engineFileWriteEnabled) {
        this.engineFileWriteEnabled = engineFileWriteEnabled;
    }

    public Rythm.Mode getRythmEngineMode() {
        return rythmEngineMode;
    }

    public void setRythmEngineMode(Rythm.Mode rythmEngineMode) {
        this.rythmEngineMode = rythmEngineMode;
    }

    public ISourceCodeEnhancer getRythmCodegenSourceCodeEnhancer() {
        return rythmCodegenSourceCodeEnhancer;
    }

    public void setRythmCodegenSourceCodeEnhancer(ISourceCodeEnhancer rythmCodegenSourceCodeEnhancer) {
        this.rythmCodegenSourceCodeEnhancer = rythmCodegenSourceCodeEnhancer;
    }
}
