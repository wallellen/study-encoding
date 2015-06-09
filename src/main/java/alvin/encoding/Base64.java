package alvin.encoding;

import java.util.Arrays;

public class Base64 {

    static private char[] ALPHA_TABLE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/', '='
    };

    static private byte[] CODES_TABLE = new byte[256];

    static {
        Arrays.fill(CODES_TABLE, (byte) 0xFF);
        for (int i = 'A'; i <= 'Z'; i++) {
            CODES_TABLE[i] = (byte) (i - 'A');
        }
        for (int i = 'a'; i <= 'z'; i++) {
            CODES_TABLE[i] = (byte) (26 + i - 'a');
        }
        for (int i = '0'; i <= '9'; i++) {
            CODES_TABLE[i] = (byte) (52 + i - '0');
        }
        CODES_TABLE['+'] = 62;
        CODES_TABLE['/'] = 63;
    }

    public String encode(byte[] data) {
        char[] out = new char[((data.length + 2) / 3) * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false, trip = false;
            int val = (0xFF & (int) data[i]) << 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data[i + 2]);
                quad = true;
            }
            out[index + 3] = ALPHA_TABLE[(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 2] = ALPHA_TABLE[(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 1] = ALPHA_TABLE[val & 0x3F];
            val >>= 6;
            out[index] = ALPHA_TABLE[val & 0x3F];
        }
        return new String(out);
    }

    public byte[] decode(String code) {
        int len = ((code.length() + 3) / 4) * 3;
        if (code.length() > 0 && code.charAt(code.length() - 1) == '=') {
            --len;
        }
        if (code.length() > 1 && code.charAt(code.length() - 2) == '=') {
            --len;
        }
        byte[] out = new byte[len];
        int shift = 0, accum = 0, index = 0;
        for (int i = 0; i < code.length(); i++) {
            int value = CODES_TABLE[(int)code.charAt(i) & 0xFF];
            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte) ((accum >> shift) & 0xff);
                }
            }
        }
        if (index != out.length) {
            throw new Error("miscalculated data length!");
        }
        return out;
    }
}
