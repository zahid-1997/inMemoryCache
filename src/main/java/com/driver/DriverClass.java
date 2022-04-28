package com.driver;

import com.inMemoryCache.Cache.Cache;


public class DriverClass {
	
	static class TestClass implements Runnable{
		Cache<String, String> cache = new Cache<>(2, 3);
		@Override
		public void run() {
			cache.put("1st", "Test1");
			cache.put("2nd", "Test2");
			cache.put("3rd", "Test3");
			System.out.println(Thread.currentThread().getName() + " " +  cache.get("1st"));
			cache.put("4th", "Test4");
			System.out.println(Thread.currentThread().getName() + " " +  cache.get("1st"));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " " + cache.get("2nd"));
			
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		
		TestClass testClass = new TestClass();
		Thread t1 = new Thread(testClass);
		Thread t2 = new Thread(testClass);
		
		t1.start();
		t2.start();
	}
	
	

}
