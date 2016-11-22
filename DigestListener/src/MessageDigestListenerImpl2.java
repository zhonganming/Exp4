import java.io.File;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class MessageDigestListenerImpl2 implements MessageDigestListener {
	private File file;
	
	public MessageDigestListenerImpl2(File file) {
		this.file = file;
	}

	public void showMessageDigest(byte[] digest) {
		System.out.println("The Message Digest of " + file + " is: " + new HexBinaryAdapter().marshal(digest));
	}
}
