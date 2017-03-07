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

    protected abstract InputStream getInputStream();

    String readLine() throws IOException {
        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(new InputStreamReader(getInputStream()));
        }
        final String readLine = bufferedReader.readLine();
        if (readLine == null) {
            closeBufferedReader();
        }
        return readLine;
    }

    private void closeBufferedReader() throws IOException {
        bufferedReader.close();
    }
}
