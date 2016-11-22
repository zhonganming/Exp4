package II1;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornTest {
	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine nashornEngine = scriptEngineManager.getEngineByName("nashorn");
		
		Integer result = (Integer) nashornEngine.eval("10 + 2");
		String s = "hello world";
		nashornEngine.eval("print('" + s + "')");
		
		System.out.println(result);
	}
}
