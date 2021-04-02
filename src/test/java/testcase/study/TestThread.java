package testcase.study;

import com.jtest.annotation.JTest;
import com.jtest.testframe.ITestFixture;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestThread extends ITestFixture {

	@Override
	public void setUp() {

	}

	@Override
	public void tearDown() {

	}

	class RtnThread implements Callable<Integer> {

		public Integer call() throws Exception {
			int i = 0;
			for (; i < 40; i++) {
				Thread.yield();
				log(Thread.currentThread().getName());
			}
			return i;
		}

	}

	public void testCallable() {
		RtnThread rt = new RtnThread();
		FutureTask<Integer> task = new FutureTask<Integer>(rt);
		for (int i = 0; i < 30; i++) {
			log(Thread.currentThread().getName());
			if (20 == i) {
				new Thread(task, "has return thread").start();
			}
		}
		try {
			while (!task.isDone())
				;
			log("Thread ret=" + task.get());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	class JoinThread extends Thread {
		public JoinThread(String string) {
			super(string);
			setPriority(Thread.MAX_PRIORITY);

		}

		public synchronized void run() {
			for (int i = 0; i < 100; i++) {
				log(getName() + " " + i);
				synchronized (this) {
					notifyAll();
				}
				yield();
			}
		}

	}

	@JTest
	public void testJoin() {
		new JoinThread("new thread").start();
		for (int i = 0; i < 100; i++) {
			if (20 == i) {
				JoinThread jt = new JoinThread("��JOIN�߳�!");
				jt.start();
				try {
					jt.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}

	class YieldThread extends Thread {
		public YieldThread(String string) {
			super(string);
			setPriority(Thread.MIN_PRIORITY);
		}

		public void run() {
			for (int i = 220; i < 281; i++) {

				log(getName() + " " + i);
				synchronized (this) {
					try {
						if (iswait == false) {
							iswait = true;
							wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				yield();

			}
		}

	}

	boolean iswait = false;
	@JTest
	public void testYieldThread() {

		new YieldThread("new  YieldThread ").start();
		new JoinThread("new JoinThread").start();

	}

	void setInteger(Integer a) {
		a = new Integer(2);
	}

	public void testParamInteger() {
		Integer x = new Integer(1);
		System.out.println(x);
		setInteger(x);
		System.out.println(x);

	}



}

