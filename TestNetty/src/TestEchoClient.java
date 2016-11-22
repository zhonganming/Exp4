import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestEchoClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 8888);
		Scanner localScanner = new Scanner(System.in);
		Scanner inetScanner = new Scanner(socket.getInputStream());
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
		String line = null;
		while ((line = localScanner.nextLine()) != null) {
			pw.println(line);
			System.out.println(inetScanner.nextLine());
		}
		
	}
}
