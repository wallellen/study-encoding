package alvin.encoding;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TestEncoding {

    @Test
    public void test_charset() throws Exception {
        final String text = "Hello大家好";
        Encoding e = new Encoding("GBK");

        byte[] data = e.encode(text);
        assertThat(text, is(e.encode(data)));
    }

    @Test
    public void test_encode_and_decode() throws Exception {
        final String text = "Hello大家好";
        byte[] data = text.getBytes("utf-8");

        assertThat(text, is(new String(data, "utf-8")));
    }
}
