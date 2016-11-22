import java.io.File;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class MessageDigestListenerImpl1 implements MessageDigestListener {
	private File file;
	
	public MessageDigestListenerImpl1(File file) {
		this.file = file;
	}

	public void showMessageDigest(byte[] digest) {
		System.out.println("文件" + file + "的消息摘要值为：" + new HexBinaryAdapter().marshal(digest));
	}
}
