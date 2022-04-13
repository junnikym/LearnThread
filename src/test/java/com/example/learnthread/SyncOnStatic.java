package com.example.learnthread;

/**
 * Class 안의 static 함수에 synchronized 키워드를 붙일 시에는
 * 해당하는 Class 전체에 Lock이 걸린다.
 */
public class SyncOnStatic {

	private static String someStr;

	public static synchronized void toSomething() {
		someStr = "something";
		try {
			long sleep = (long)(Math.random() * 20);
			Thread.sleep(sleep);
			if( !someStr.equals("something") )
				System.out.println("sync broken - something");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void toSomeone() {
		someStr = "someone";
		try {
			long sleep = (long)(Math.random() * 10);
			Thread.sleep(sleep);
			if( !someStr.equals("someone") )
				System.out.println("sync broken - someone");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
