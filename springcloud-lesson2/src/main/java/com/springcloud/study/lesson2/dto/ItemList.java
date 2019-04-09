package com.springcloud.study.lesson2.dto;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
	public static List<ItemBean> items = new ArrayList<ItemBean>();

	static {
		ItemBean u1 = new ItemBean();
		u1.setId(1);
		u1.setName("name1");
		items.add(u1);
		ItemBean u2 = new ItemBean();
		u2.setId(2);
		u2.setName("name2");
		items.add(u2);
	};
}
