package com.inMemoryCache.Cache;

import java.util.LinkedList;
import java.util.Queue;

import com.inMemoryCache.Cache.CustomLRUMap.LRU;

public class Cache<K, T> {

	private final long timeToLive;

	private final CustomLRUMap cacheMap;

	protected class Value {
		public long lastaccessed = System.currentTimeMillis();
		private T value;

		protected Value(T value) {
			this.setValue(value);
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

	}

	public Cache(long timeToLive, int maxItems) {
		this.timeToLive = timeToLive * 1000;
		cacheMap = new CustomLRUMap<>(maxItems);

		if (timeToLive > 0) {

			Thread t = new Thread(new Runnable() {

				public void run() {
					while (true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						cleanUpCache();
					}

				}
			});

			t.setDaemon(true);
			t.start();
		}

	}

	public void put(K key, T value) {
		synchronized (this.getClass()) {
			cacheMap.put(key, new Value(value));
		}
	}

	public T get(K key) {
		synchronized (this.getClass()) {
			Value val = (Value) cacheMap.get(key);
			if (val == null) {
				return null;
			}
			val.lastaccessed = System.currentTimeMillis();
			return val.getValue();
		}
	}

	public void remove(K key) {
		synchronized (this.getClass()) {
			cacheMap.remove(key);
		}
	}

	public int size() {
		synchronized (this.getClass()) {
			return cacheMap.size();
		}
	}

	protected void cleanUpCache() {

		long now = System.currentTimeMillis();
		Queue<K> cleanUpQueue = new LinkedList<K>();

		synchronized (cacheMap) {
			LRU head = cacheMap.head;
			while (head != null) {
				K k = (K) head.key;
				Value val = (Value) head.value;

				if (val != null && (now > (timeToLive + val.lastaccessed))) {
					cleanUpQueue.offer(k);
				}

				head = head.next;

			}

		}

		for (K key : cleanUpQueue) {
			synchronized (cacheMap) {
				cacheMap.remove(key);
			}

			Thread.yield();
		}

	}

}
