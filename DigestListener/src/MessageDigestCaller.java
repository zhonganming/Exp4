import java.io.File;

public class MessageDigestCaller {
	public static void main(String[] args) throws Exception{
		File file = new File("jdk-8u5-apidocs.chm");
		MessageDigestListenerImpl1 messageDigestListenerImpl1 = new MessageDigestListenerImpl1(file);
		MessageDigestListenerImpl2 messageDigestListenerImpl2 = new MessageDigestListenerImpl2(file);
		MessageDigestListenerImpl3 messageDigestListenerImpl3 = new MessageDigestListenerImpl3(file);
		MessageDigestCalculator messageDigestCalculator = new MessageDigestCalculator(file);
		messageDigestCalculator.addMessageDigestListener(messageDigestListenerImpl1);
		new Thread(messageDigestCalculator).start();
		//Thread.sleep(15);
		messageDigestCalculator.addMessageDigestListener(messageDigestListenerImpl2);
		messageDigestCalculator.addMessageDigestListener(messageDigestListenerImpl3);
		

	}
}
