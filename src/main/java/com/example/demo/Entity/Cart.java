package com.example.demo.Entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Integer,Item> items=new HashMap<>();
	
	private int total;

	public Map<Integer, Item> getItems() {
		return items;
	}
	
	public Cart() {
		super();
	}

	public void setItem(Map<Integer, Item> items) {
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * アイテム情報をカートに追加
	 * @param item
	 * @param quantity
	 */
	public void addCart(Item item,int quantity) {
		Item existedItem = items.get(item.getId());
		
		if(existedItem==null) {
			item.setQuantity(quantity);
			items.put(item.getId(), item);
		}else {
			existedItem.setQuantity(existedItem.getQuantity()+quantity);
		}
		recalcTotal();
	}
	/**
	 * カートの削除処理
	 * @param itemCode
	 */
	public void deleteCart(int itemCode) {
		items.remove(itemCode);
		recalcTotal();
	}
	
	/**
	 * カートの小計を算出
	 */
	public void recalcTotal() {
		total=0;
		for(Item item : items.values()) {
			total+=item.getPrice()*item.getQuantity();
		}
	}
}
