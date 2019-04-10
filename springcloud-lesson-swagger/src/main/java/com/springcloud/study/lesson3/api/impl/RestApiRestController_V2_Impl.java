package com.springcloud.study.lesson3.api.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.study.lesson3.api.RestApiRestController_V2;
import com.springcloud.study.lesson3.bean.ItemBean;
import com.springcloud.study.lesson3.bean.ItemList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "Rest接口API样例_V3")
@RestController
@RequestMapping("/api2")
public class RestApiRestController_V2_Impl implements RestApiRestController_V2 {

	@Override
	@GetMapping(value = "/listItem")
	public @ResponseBody List<ItemBean> listItem() {
		System.out.println("----------/listItem , listItem------------");
		return ItemList.items;
	}

	@Override
	@GetMapping(value = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ItemBean getItem(@PathVariable("id") int id) {
		System.out.println("----------/item/{id} , getItem------------");
		ItemBean item = ItemList.items.get(id);
		System.out.println("getItem where id=" + id);
		return item;
	}

	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@ApiImplicitParam(name = "item", value = "用户详细实体user", required = true, dataType = "ItemBean")
	@Override
	@PostMapping(value = "/item")
	public ResponseEntity<Long> createItem(@RequestBody ItemBean item) {
		System.out.println("----------/item , createItem------------");
		ItemList.items.add(item);
		System.out.println("create item where id=" + item.getId() + " and name=" + item.getName());
		return new ResponseEntity<Long>(item.getId(), HttpStatus.OK);
	}

	@Override
	@PutMapping(value = "/item/{id}")
	public ResponseEntity<Void> updateItem(@PathVariable("id") long id, @RequestBody ItemBean item) {
		System.out.println("----------/item/{id} , updateItem------------");
		ItemBean currentItem = new ItemBean();
		currentItem.setId(id);
		currentItem.setName(item.getName());
		ItemList.items.add(currentItem);
		System.out.println("update item where id=" + id + " and name=" + item.getName());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	@DeleteMapping(value = "/item/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable("id") long id) {
		System.out.println("----------/item/{id} , deleteItem------------");
		ItemBean item = ItemList.items.get(0);
		if (item == null) {
			System.out.println("Unable to delete. Item with id " + id + " not found");
		}
		System.out.println("Deleting item where id=" + id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	@DeleteMapping(value = "/item")
	public ResponseEntity<Void> deleteAllitems() {
		System.out.println("----------/item , deleteAllitems------------");
		System.out.println("Deleting All items");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
