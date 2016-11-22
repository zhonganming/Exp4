import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestExecutorAndCallable {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				long currentTime = System.currentTimeMillis();
				while (System.currentTimeMillis() < currentTime + 5000) {
					
				}
				return "Finished";
			}
		});
		System.out.println("Doing");
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
