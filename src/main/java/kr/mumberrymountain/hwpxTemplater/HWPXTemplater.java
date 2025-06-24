package kr.mumberrymountain.hwpxTemplater;

import kr.dogfoot.hwpxlib.writer.HWPXWriter;
import kr.mumberrymountain.hwpxTemplater.exception.TemplateParsingException;
import kr.mumberrymountain.hwpxTemplater.exception.TemplateRenderingException;
import kr.mumberrymountain.hwpxTemplater.interceptor.Interceptor;
import kr.mumberrymountain.hwpxTemplater.interceptor.InterceptorHandler;
import kr.mumberrymountain.hwpxTemplater.parse.HWPXParser;
import kr.dogfoot.hwpxlib.object.HWPXFile;
import kr.dogfoot.hwpxlib.reader.HWPXReader;
import kr.mumberrymountain.hwpxTemplater.render.HWPXRenderer;

import java.io.OutputStream;
import java.util.Map;

public class HWPXTemplater {

    private final HWPXFile hwpxFile;
    private final Config config;

    private final InterceptorHandler interceptorHandler;


    public HWPXTemplater(String filePath) {
        try {
            this.hwpxFile = HWPXReader.fromFilepath(filePath);
            this.config = new Config().validate();
            this.interceptorHandler = new InterceptorHandler();

            new HWPXParser(hwpxFile, config).parse();
        } catch (Exception e) {
            throw new TemplateParsingException("Unexpected error occurred while parsing the template", e);
        }

    }

    public HWPXTemplater(String filePath, Config config) {
        try {
            this.hwpxFile = HWPXReader.fromFilepath(filePath);
            this.config = config.validate();
            this.interceptorHandler = new InterceptorHandler();
            new HWPXParser(hwpxFile, config).parse();
        } catch (Exception e) {
            throw new TemplateParsingException("Unexpected error occurred while parsing the template", e);
        }
    }

    public HWPXTemplater(String filePath, Config config, InterceptorHandler interceptorHandler) {
        try{
            this.hwpxFile = HWPXReader.fromFilepath(filePath);
            this.config = config.validate();
            this.interceptorHandler = interceptorHandler;

            new HWPXParser(hwpxFile, config).parse();
        } catch (Exception e) {
            throw new TemplateParsingException("Unexpected error occurred while parsing the template", e);
        }
    }

    public <T> HWPXTemplater render(Map<String, T> data) {
        try {
            new HWPXRenderer<>(hwpxFile, config, interceptorHandler, data).render();
            return this;
        } catch (Exception e) {
            throw new TemplateRenderingException("Unexpected error occurred while rendering the template", e);
        }
    }

    public HWPXFile getFile(){
        return hwpxFile;
    }

    public HWPXTemplater write(OutputStream outputStream) throws Exception {
        HWPXWriter.toStream(hwpxFile, outputStream);
        return this;
    }

    public static builder builder() {
        return new builder();
    }

    public static class builder {
        private Config config = new Config();
        private InterceptorHandler interceptorHandler = new InterceptorHandler();

        private HWPXTemplater hwpxTemplater;

        public builder config(String key, Object value){
            config.set(key, value);
            return this;
        }

        public builder interceptor(Interceptor interceptor){
            interceptorHandler.register(interceptor);
            return this;
        }

        public HWPXTemplater parse(String filePath) {
            hwpxTemplater = new HWPXTemplater(filePath, config, interceptorHandler);
            return hwpxTemplater;
        }

        public <T> HWPXTemplater render(Map<String, T> data) {
            hwpxTemplater.render(data);
            return hwpxTemplater;
        }

        public HWPXTemplater write(OutputStream outputStream) throws Exception {
            hwpxTemplater.write(outputStream);
            return hwpxTemplater;
        }
    }
}
