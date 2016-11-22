import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class TestCallable {
	Socket socket;
	
	public static void main(String[] args) {
		for (String s : args) {
			File file = new File(s);
			if (file.isFile()) {
				FutureTask<String> futureTask = new FutureTask<String>(new Task(file));
				Thread t = new Thread(futureTask);
				t.start();
				try {
					System.out.println(futureTask.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String path = file.getAbsoluteFile().getPath();
			Socket socket = new Socket();
			String ss = path;
			ss = path + "aa";
			File file2 = new File("aa.txt");
		}
		
		
	}
}

class Task implements Callable<String> {
	File file;
		
	public Task(File file) {
		this.file = file;
	}


	public String call() throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md);
		byte[] b = new byte[1024];
		while(dis.read(b) == 1024);
		byte[] digest = md.digest();
		dis.close();
		return "The Message Digest of File " + file + " is " + new HexBinaryAdapter().marshal(digest);		
	}
	
}