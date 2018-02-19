package uk.co.redkiteweb.dccweb.data.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by shawn on 26/01/17.
 */
public abstract class AbstractReader {

    private BufferedReader bufferedReader = null;

    protected InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(getFileName());
    }

    protected abstract String getFileName();

    protected String readLine() {
        String readLine;
        try {
            if (bufferedReader == null) {
                bufferedReader = new BufferedReader(new InputStreamReader(getInputStream()));
            }
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                closeBufferedReader();
            }
        } catch (IOException exception) {
            throw new ReaderException(String.format("Unable to read %s", getFileName()), exception);
        }
        return readLine;
    }

    private void closeBufferedReader() throws IOException {
        bufferedReader.close();
    }
}
