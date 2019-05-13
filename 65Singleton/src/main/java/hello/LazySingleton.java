package hello;

public class LazySingleton {

	private LazySingleton() {
	}

	private static LazySingleton instance;

	public static synchronized LazySingleton getInstance() {
		if (instance == null) {
			return instance = new LazySingleton();//2 object by 2 thread
		} else {
			return instance;
		}
	}
	
	/*public static LazySingleton getInstance() {
		if (instance == null) {
			return instance = new LazySingleton();//2 object by 2 thread
		} else {
			return instance;
		}
	}*/

}
