import java.io.File;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class MessageDigestListenerImpl1 implements MessageDigestListener {
	private File file;
	
	public MessageDigestListenerImpl1(File file) {
		this.file = file;
	}

	public void showMessageDigest(byte[] digest) {
		System.out.println("�ļ�" + file + "����ϢժҪֵΪ��" + new HexBinaryAdapter().marshal(digest));
	}
}
