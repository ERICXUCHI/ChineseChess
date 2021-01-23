import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteFileDemo {
    public static void main(String[] args) {

        try {
            FileOutputStream fos = new FileOutputStream(new File("1_w.chessboard"));
            OutputStreamWriter osr = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osr);

            bw.write("CHEAGAEHC\n");
            bw.flush();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
