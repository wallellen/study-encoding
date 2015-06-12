package alvin.encoding;

import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TestEncoding {

    @Test
    public void test_charset() throws Exception {
        final String text = "Hello大家好";
        Encoding e = new Encoding("GBK");

        byte[] data = e.encode(text);
        assertThat(text, is(e.decode(data)));
    }

    @Test
    public void test_encode_and_decode() throws Exception {
        final String text = "Hello大家好";
        byte[] data = text.getBytes("utf-8");

        assertThat(text, is(new String(data, "utf-8")));
    }

    @Test
    public void test_file_encode() throws Exception {
        try {
            FileEncoding fe = new FileEncoding("UTF-8", "test.txt");
            fe.write("Hello大家好");

            assertThat(fe.read(), is("Hello大家好"));
            assertThat(fe.readRaw(), is("Hello大家好".getBytes("UTF-8")));
        } finally {
            Files.delete(Paths.get("test.txt"));
        }
    }

    private String RFC3986Encode(byte[] utf8) {
        StringBuilder sb = new StringBuilder();
        for (byte b : utf8) {
            sb.append("%");
            sb.append(String.format("%X", b));
        }
        return sb.toString();
    }

    @Test
    public void test_url_encoding() throws Exception {
        final String src = "编码";
        assertThat(RFC3986Encode(src.getBytes("UTF-8")), is(URLEncoder.encode(src, "UTF-8")));
        assertThat(URLDecoder.decode(RFC3986Encode(src.getBytes("UTF-8")), "UTF-8"), is(src));
    }

    @Test
    public void test_base64() throws Exception {
        final byte[] srcData = Files.readAllBytes(Paths.get(getClass().getResource("/girl.jpg").toURI()));

        Base64 base64 = new Base64();
        String b64 = base64.encode(srcData);
        byte[] decodeData = base64.decode(b64);

        assertThat(decodeData.length, is(srcData.length));
        for (int i = 0; i < decodeData.length; i++) {
            assertThat(decodeData[i], is(srcData[i]));
        }
    }
}
