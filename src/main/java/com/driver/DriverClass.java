package com.driver;

import com.inMemoryCache.Cache.Cache;


public class DriverClass {
	
	static class TestClass{
		
		private void TestCache() throws InterruptedException {

			Cache<String, String> cache = new Cache<>(2, 3);
			cache.put("1st", "Test1");
			cache.put("2nd", "Test2");
			cache.put("3rd", "Test3");
			System.out.println(cache.get("1st"));
			cache.put("4th", "Test4");
			System.out.println(cache.get("1st"));
			Thread.sleep(3000);
			System.out.println(cache.get("2nd"));
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		TestClass testClass = new TestClass();
		testClass.TestCache();
	}
	
	

}
