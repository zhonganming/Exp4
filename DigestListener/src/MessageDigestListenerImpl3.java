import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class MessageDigestListenerImpl3 implements MessageDigestListener {
	private File file;
	
	public MessageDigestListenerImpl3(File file) {
		this.file = file;
	}

	public void showMessageDigest(byte[] digest) {
		
		JOptionPane.showMessageDialog(null, "The Message Digest of " + file + "is: " + new HexBinaryAdapter().marshal(digest));
	}
}
