package alvin.encoding;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileEncoding {
    private Charset charset;
    private Path file;
    private OpenOption[] openOption;

    public FileEncoding(String charset, String file, OpenOption... openOption) {
        this.charset = Charset.forName(charset);
        this.file = Paths.get(file);
        this.openOption = openOption;
    }

    public FileEncoding(String charset, String file) {
        this(charset, file, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
    }

    public void write(String content) throws IOException {
        try (Writer writer = Files.newBufferedWriter(file, charset, openOption)) {
            writer.write(content);
        }
    }

    public String read() throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[512];
        int len;
        try (Reader reader = Files.newBufferedReader(file, charset)) {
            while ((len = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, len);
            }
        }
        return sb.toString();
    }

    public byte[] readRaw() throws IOException {
        byte[] raw = new byte[(int) Files.size(file)];
        try (InputStream is = new BufferedInputStream(Files.newInputStream(file))) {
            is.read(raw);
        }
        return raw;
    }
}
