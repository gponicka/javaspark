import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.ModelAndView;
import spark.Spark;
import spark.TemplateEngine;

import java.io.IOException;
import java.io.StringWriter;

public class FreemarkerEngine extends TemplateEngine {

    private Configuration configuration;

    public FreemarkerEngine() throws IOException {
        this.configuration = createDefaultConfiguration();
    }

    @Override
    public String render(ModelAndView modelAndView) {
        try {
            StringWriter stringWriter = new StringWriter();

            Template template = configuration.getTemplate(modelAndView.getViewName());
            template.process(modelAndView.getModel(), stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Configuration createDefaultConfiguration() throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        configuration.setClassForTemplateLoading(FreemarkerEngine.class, "");
        return configuration;
    }
}
