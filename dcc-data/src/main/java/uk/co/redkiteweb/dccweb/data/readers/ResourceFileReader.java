package uk.co.redkiteweb.dccweb.data.readers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
@Scope("prototype")
public class ResourceFileReader {

    private BufferedReader bufferedReader = null;
    private String resourceFile;

    public void setResourceFile(final String resourceFile) {
        this.resourceFile = resourceFile;
    }

    private InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(resourceFile);
    }


    public String readLine() {
        String readLine;
        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(new InputStreamReader(getInputStream()));
        }
        try {
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                closeBufferedReader();
            }
        } catch (IOException exception) {
            throw new ReaderException(String.format("Unable to read %s", resourceFile), exception);
        }
        return readLine;
    }

    private void closeBufferedReader() throws IOException {
        bufferedReader.close();
    }
}
