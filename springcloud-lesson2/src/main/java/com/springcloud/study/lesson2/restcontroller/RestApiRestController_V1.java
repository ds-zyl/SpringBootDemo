package com.springcloud.study.lesson2.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.study.lesson2.dto.ItemBean;
import com.springcloud.study.lesson2.dto.ItemList;

/**
 * Rest接口，方式：@RestController+@RequestMapping
 */
@RestController
@RequestMapping("/api1")
public class RestApiRestController_V1 {

	/**
	 * GET 方式请求 /api1/listitem 返回用户列表
	 * http://localhost:8080/api1/listItem
	 */
	@RequestMapping(value = "/listItem", method = RequestMethod.GET)
	public @ResponseBody List<ItemBean> listItem() {
		System.out.println("----------/listItem , listItem------------");
		return ItemList.items;
	}

	/**
	 * GET 方式请求 /api1/item/1返回id为1的用户
	 * http://localhost:8080/api1/item/0
	 */
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ItemBean getItem(@PathVariable("id") int id) {
		System.out.println("----------/item/{id} , getItem------------");
		ItemBean item = ItemList.items.get(id);
		System.out.println("getItem where id=" + id);
		return item;
	}

	/**
	 * POST 方式请求 /api1/item 通过item对象的JSON 参数创建新的item对象
	 * http://localhost:8080/api1/item
	 */
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public ResponseEntity<Long> createItem(@RequestBody ItemBean item) {
		System.out.println("----------/item , createItem------------");
		ItemList.items.add(item);
		System.out.println("create item where id=" + item.getId() + " and name=" + item.getName());
		return new ResponseEntity<Long>(item.getId(), HttpStatus.OK);
	}

	/**
	 * PUT 方式请求 /api1/item/1 更新id为1的发送json格式的用户对象
	 * http://localhost:8080/api1/item/0
	 */
	@RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateItem(@PathVariable("id") long id, @RequestBody ItemBean item) {
		System.out.println("----------/item/{id} , updateItem------------");
		ItemBean currentItem = new ItemBean();
		currentItem.setId(id);
		currentItem.setName(item.getName());
		ItemList.items.add(currentItem);
		System.out.println("update item where id=" + id + " and name=" + item.getName());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * DELETE 方式请求/api1/item/4删除 ID为 4的item对象
	 * http://localhost:8080/api1/item/0
	 */
	@RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteItem(@PathVariable("id") long id) {
		System.out.println("----------/item/{id} , deleteItem------------");
		ItemBean item = ItemList.items.get(0);
		if (item == null) {
			System.out.println("Unable to delete. Item with id " + id + " not found");
		}
		System.out.println("Deleting item where id=" + id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * DELETE 方式请求/api1/item/删除所有item
	 * http://localhost:8080/api1/item
	 */
	@RequestMapping(value = "/item", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllitems() {
		System.out.println("----------/item , deleteAllitems------------");
		System.out.println("Deleting All items");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
