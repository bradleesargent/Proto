package proto.reader;

import junit.framework.TestCase;

import static org.junit.Assert.*;


/**
 * Created by bradl on 3/24/2019
 * Amount byte number 0  0x40
 * Amount byte number 1  0x82
 * Amount byte number 2  0xe2
 * Amount byte number 3  0x31
 * Amount byte number 4  0xd6
 * Amount byte number 5  0xd7
 * Amount byte number 6  0x2e
 * Amount byte number 7  0x8d
 */
public class ProtoTest extends TestCase {
    byte [] sampleByteArray = new byte[] {0x40, (byte) 0x82, (byte) 0xE2 , (byte) 0x31 ,(byte) 0xd6, (byte) 0xd7,(byte) 0x2e, (byte) 0x8d };
    byte [] actualExampleByteArray = new byte[] {(byte)0x44,(byte) 0x17,(byte) 0x11,(byte) 0x8f,(byte) 0x44,(byte) 0x17,(byte) 0x11,(byte) 0x8f};
    public void testBytes() {
        byte [] bytesForSample = floatToByteArray(604.274335557087F );
        for (int i = 0;i<bytesForSample.length;i++) {
            System.out.println(String.format("i=%d byte=0x%x",i,bytesForSample[i]));
        }
        assertTrue(true);

    }

    public void testFloat() {
        float sampleFloat  = byteArrayToFloat(sampleByteArray );
        System.out.println(String.format("Floating number using sample byte array=%f",sampleFloat));
        assertTrue(true);

    }

    public void testFloatActualExample() {
        float sampleFloat  = byteArrayToFloat(actualExampleByteArray);
        System.out.println(String.format("Floating number actual example=%f",sampleFloat));
        assertTrue(true);

    }
    public void testFloatReverse() {
        float sampleFloat  = byteArrayToFloatReverse(sampleByteArray );
        System.out.println(String.format("Floating number using reverse order=%f",sampleFloat));
        assertTrue(true);

    }
    public static float byteArrayToFloat(byte[] bytes) {
        int intBits =
                        bytes[0] << 56 |
                        (bytes[1] & 0xFF) << 48 |
                        (bytes[2] & 0xFF) << 40 |
                        (bytes[3] & 0xFF) << 32 |
                        (bytes[4] & 0xFF) << 24 |
                        (bytes[5] & 0xFF) << 16 |
                        (bytes[6] & 0xFF) << 8 |
                        (bytes[7] & 0xFF);
        return Float.intBitsToFloat(intBits);
    }
    public static float byteArrayToFloatReverse(byte[] bytes) {
        int intBits =
                bytes[7] << 56 |
                        (bytes[6] & 0xFF) << 48 |
                        (bytes[5] & 0xFF) << 40 |
                        (bytes[4] & 0xFF) << 32 |
                        (bytes[3] & 0xFF) << 24 |
                        (bytes[2] & 0xFF) << 16 |
                        (bytes[1] & 0xFF) << 8 |
                        (bytes[0] & 0xFF);
        return Float.intBitsToFloat(intBits);
    }
    public static byte[] floatToByteArray(float value) {
        int intBits =  Float.floatToIntBits(value);
        return new byte[] {
                (byte) (intBits >> 56),// byte 7
                (byte) (intBits >> 48),// byte 6
                (byte) (intBits >> 40),// byte 5
                (byte) (intBits >> 32),// byte 4
                (byte) (intBits >> 24),// byte 3
                (byte) (intBits >> 16),// byte 2
                (byte) (intBits >> 8), // byte 1
                (byte) (intBits) };    // byte 0
    }


}