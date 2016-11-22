package II1;

import java.util.Map;
import java.util.function.BiConsumer;

public class Test {
	public static void main(String[] args) {
		System.out.println(System.lineSeparator());
		System.out.println(System.nanoTime());
		System.out.println(System.currentTimeMillis());
		Map<String, String> map = System.getenv();
//		for(Map.Entry<String, String> entry : map.entrySet()) {
//			System.out.println(entry);
//		}
		
		map.forEach(new BiConsumer<String, String>() {
			@Override
			public void accept(String t, String u) {
				System.out.println(u + t);
			}			
		});
	}
}
