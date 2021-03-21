package w1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class _2 {


    public static void main(String[] args) {
        try {
            Object hello = new XlassClassLoader().findClass("Hello").newInstance();
            hello.getClass().getDeclaredMethod("hello").invoke(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class XlassClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) {
            byte[] bytes = processFile(name);
            return defineClass(name, bytes, 0, bytes.length);
        }

        private byte[] processFile(String className) {
            FileInputStream fileInputStream = null;
            try {
                String fileName = className + ".xlass";
                File file = new File(fileName);
                int length = (int) file.length();
                byte[] bytes = new byte[length];
                byte[] result = new byte[length];
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(bytes);
                for (int i = 0; i < bytes.length; i++) {
                    byte aByte = bytes[i];
                    result[i] = (byte) (255 - aByte);
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }

}
