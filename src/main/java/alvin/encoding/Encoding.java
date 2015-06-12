package alvin.encoding;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Encoding {

    private Charset charset;

    public Encoding(String charsetName) {
        this.charset = Charset.forName(charsetName);
    }

    public byte[] encode(String text) {
        ByteBuffer buffer = charset.encode(text);
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        return data;
    }

    public String decode(byte[] data) {
        return charset.decode(ByteBuffer.wrap(data)).toString();
    }
}
