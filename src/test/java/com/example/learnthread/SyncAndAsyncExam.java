package com.example.learnthread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// https://tourspace.tistory.com/54
public class SyncAndAsyncExam {

	//////////////////////////////
	// Synchronized Method 사용
	//////////////////////////////

	class SyncMethodExam {

		private String who;

		public synchronized void callMeSync(String who) {
			this.who = who;

			try {
				long sleep = (long) (Math.random() * 10);
				Thread.sleep(sleep);
			} catch (Exception e) { }

			if( !this.who.equals(who) ) {
				System.out.println(this.who + " | " + who);
			}
		}

		public void callMeWithoutThread(String who) {
			this.who = who;

			try {
				long sleep = (long) (Math.random() * 10);
				Thread.sleep(sleep);
			} catch (Exception e) { }

			if( !this.who.equals(who) ) {
				System.out.println(this.who + " | " + who);
			}
		}

	}

	/**
	 * #callMeSync 함수를 호출 시 다음과 같은 작업을 진행
	 *
	 * 1. 인자값을 멤버필드에 저장
	 * 2. 랜덤하게 Sleep
	 * 3. 같지 않으면 Log 출력
	 */
	@Test
	void SyncExam_synchronize_키워드_존재() {
		SyncMethodExam syncExam = new SyncMethodExam();

		System.out.println("Test Function In. -----");

		new Thread(()-> {
			for(int i = 0; i < 1000; i++)
				syncExam.callMeSync("Thread A");
		}).start();

		new Thread(()-> {
			for(int i = 0; i < 1000; i++)
				syncExam.callMeSync("Thread B");
		}).start();

		System.out.println("Test Function Out. ----");
	}

	/**
	 * #callMeSync 함수와 달리 맴버필드 who가 각기 다른 쓰레드의 영향을 받아
	 * 인자를 저장한 맴버필드와 인자값이 다른 현상이 발생하게 됨.
	 */
	@Test
	void SyncExam_synchronize_키워드_없음() {
		SyncMethodExam syncExam = new SyncMethodExam();

		System.out.println("Test Function In. -----");

		new Thread(()-> {
			for(int i = 0; i < 1000; i++)
				syncExam.callMeWithoutThread("Thread A");
		}).start();

		new Thread(()-> {
			for(int i = 0; i < 1000; i++)
				syncExam.callMeWithoutThread("Thread B");
		}).start();

		System.out.println("Test Function Out. ----");
	}

	//////////////////////////////
	// Synchronized Block 사용
	//////////////////////////////

	class SyncBlockExam {

		private List list = new ArrayList<Integer>();

		public void add(Integer val) {

			synchronized(this) {
				if(list.contains(val) == false) {
					list.add(val);
				}
			}

		}

		public int getSize() {
			return list.size();
		}

	}

	/**
	 * synchronized를 제거한다면 10000보다 높은 숫자가 출력될 것.
	 */
	@Test
	void SyncExam_synchronize_block() throws InterruptedException {
		SyncBlockExam syncExam = new SyncBlockExam();

		System.out.println("Test Function In. -----");

		Thread A = new Thread(()-> {
			for(int i = 0; i < 10000; i++)
				syncExam.add(i);
		});

		Thread B = new Thread(()-> {
			for(int i = 0; i < 10000; i++)
				syncExam.add(i);
		});

		A.start();
		B.start();

		A.join();
		B.join();

		System.out.println(syncExam.getSize());

		System.out.println("Test Function Out. ----");
	}

}
