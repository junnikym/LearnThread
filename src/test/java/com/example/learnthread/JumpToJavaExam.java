package com.example.learnthread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

// https://wikidocs.net/230
public class JumpToJavaExam {

	class Sample extends Thread {

		int seq;

		public Sample(int seq) {
			this.seq = seq;
		}

		public void run() {
			System.out.println(this.seq + " Thread In.");

			try {
				Thread.sleep(1000);
			} catch (Exception e) {}

			System.out.println(this.seq + " Thread Out.");
		}
	}

	@Test
	void SampleThread_함수가_먼저_종료() throws Exception {
		for(int i = 0; i < 10; i++) {
			Sample sample = new Sample(i);
			sample.start();
		}
		System.out.println("Function Out.");
	}

	@Test
	void SampleThread_쓰래드_종료_후_함수_종료() throws Exception {
		ArrayList<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Sample sample = new Sample(i);
			sample.start();
			threads.add(sample);
		}

		for(int i = 0; i < 10; i++) {
			Thread t = threads.get(i);
			try {
				t.join();
			} catch (Exception e) {}
		}

		System.out.println("Function Out.");
	}

	class RunnableSample implements Runnable {

		int seq;

		public RunnableSample(int seq) {
			this.seq = seq;
		}

		public void run() {
			System.out.println(this.seq + " Thread In.");

			try {
				Thread.sleep(1000);
			} catch (Exception e) {}

			System.out.println(this.seq + " Thread Out.");
		}

	}

	@Test
	void SampleThread_쓰래드_Runnable_인터페이스_사용() throws Exception {

		ArrayList<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread( new RunnableSample(i) ); // Thread 생성자에 Runnable 객체 전달
			t.start();
			threads.add(t);
		}

		for(int i = 0; i < 10; i++) {
			Thread t = threads.get(i);
			try {
				t.join();
			} catch (Exception e) {}
		}

		System.out.println("Function Out.");
	}

}
