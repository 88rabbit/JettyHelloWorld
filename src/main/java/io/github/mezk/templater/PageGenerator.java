package io.github.mezk.templater;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Andrei Selkin
 */
public final class PageGenerator {

    /**Public html directory.*/
    private static final String HTML_DIR = "public_html";
    /**The configuration settings of FreeMarker.*/
    private static final Configuration CFG = new Configuration();

    /**Default constructor.*/
    private PageGenerator() { }

    /**
     * Gets string representation of page where the output of the template will go.
     * @param filename file with template.
     * @param data the holder of the variables visible from the template (name-value pairs).
     * @return the string where the output of the template will go.
     */
    public static String getPage(String filename, Map<String, Object> data) {
        final Writer stream = new StringWriter();
        try {
            final Template template = CFG.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        }
        catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }
}
