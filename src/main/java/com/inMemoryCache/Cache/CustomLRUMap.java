package com.inMemoryCache.Cache;

import com.inMemoryCache.Cache.Cache.Value;

public class CustomLRUMap<K, T> {
	int size;
	int sizePointer;
	LRU head = null;
	LRU tail = null;

	class LRU {

		K key;
		Value value;
		LRU next;

		LRU(K key, Value value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}

	}

	public CustomLRUMap(int size) {
		this.size = size;
		this.sizePointer = 0;
	}

	public void put(K key, Value value) {
		LRU node = new LRU(key, value);
		if (sizePointer == size && head != null) {
			head = head.next;
			sizePointer -= 1;
		}

		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		sizePointer += 1;

	}

	public Value get(K key) {
		if (head == null) {
			return null;
		}
		LRU currentNode = head;
		while (currentNode != null) {
			if (currentNode.key.equals(key)) {
				return (Value) currentNode.value;
			}
			currentNode = currentNode.next;
		}

		return null;
	}

	public void remove(K key) {
		if (head == null) {
			return;
		}
		if (head.key.equals(key)) {
			head = head.next;
			sizePointer -= 1;
			return;
		}
		LRU currentNode = head.next;
		LRU previousNode = head;

		while (currentNode != null) {
			if (currentNode.key.equals(key)) {
				previousNode.next = currentNode.next;
				sizePointer -= 1;
				break;
			}
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
	}

	public int size() {
		return sizePointer;
	}

}
