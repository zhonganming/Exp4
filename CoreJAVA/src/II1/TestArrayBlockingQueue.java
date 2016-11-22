package II1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

import javax.print.DocFlavor.STRING;

public class TestArrayBlockingQueue {
	private static BlockingQueue<String> LineQueue = new LinkedBlockingQueue<String>();
	private static String DUMMY = "";
	
	Executor executor = null;
	
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			public void run() {
				String line = null;
				try (Scanner scanner = new Scanner(new FileInputStream("FDSdata2.txt"))){
					while (scanner.hasNext()) {
						line = scanner.nextLine();
						System.err.println("read: " + line);
						LineQueue.put(line);
						Thread.sleep(10);
					}
					LineQueue.put(DUMMY);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String line = null;
				try {
					while (!(line = LineQueue.take()).equals(DUMMY)) {
						System.out.println(line);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	}

}
