import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageDigestCalculator implements  Runnable {
	private File file;
	private BlockingQueue<MessageDigestListener> messageDigestListeners;
	

	public MessageDigestCalculator(File file) {
		this.file = file;
		messageDigestListeners = new LinkedBlockingQueue<MessageDigestListener>(); 
	}
	
	public void addMessageDigestListener(MessageDigestListener listener) throws InterruptedException {
		messageDigestListeners.put(listener);
	}
	
	public void run() {
		try {
			FileInputStream fis = new FileInputStream(file);
			MessageDigest md = MessageDigest.getInstance("SHA1");
			DigestInputStream dis = new DigestInputStream(fis, md);
			byte[] b = new byte[2048];
			while((dis.read(b)) != b.length);
			byte[] digest = md.digest();
			for(MessageDigestListener m: messageDigestListeners) {
				m.showMessageDigest(digest);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
