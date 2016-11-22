import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TestECB {
	public static void main(String[] args) throws Exception {
		SecretKey key = new SecretKeySpec(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 }, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		FileInputStream fis = new FileInputStream("Lena.bmp");
		FileOutputStream fos = new FileOutputStream("newLenna.bmp");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		CipherOutputStream cis = new CipherOutputStream(fos, cipher);
		int b = -1;
		int n = 0;
		while ((b = fis.read()) != -1) {
			n++;
			if (n < 0x23)
				fos.write(b);
			else
				cis.write(b);
		}
		cis.close();
		fis.close();
	}
}
