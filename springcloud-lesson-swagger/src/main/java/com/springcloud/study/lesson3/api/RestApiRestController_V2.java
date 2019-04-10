package com.springcloud.study.lesson3.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.springcloud.study.lesson3.bean.ItemBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Rest接口API样例_V2")
public interface RestApiRestController_V2 {
	/**
	 * 没有notes="***"
	 */
	@ApiOperation(value = "获取项目列表")
	public List<ItemBean> listItem();
	
	/**
	 * 有notes="***"
	 */
	@ApiOperation(value = "删除全部项目", notes = "删除全部项目对象")
	public ResponseEntity<Void> deleteAllitems();

	/**
	 * 没有paramType="***" , 有defaultValue
	 */
	@ApiOperation(value = "获取项目信息", notes = "根据id获取项目信息")
	@ApiImplicitParam(name = "id", value = "项目id", defaultValue="0" , required = true, dataType = "ItemBean")
	public ItemBean getItem(int id);
	
	/**
	 * 有paramType="***"
	 */
	@ApiOperation(value = "删除项目", notes = "根据ID删除对应的项目")
	@ApiImplicitParam(paramType="query", name = "id", value = "项目id", required = true, dataType = "long")
	public ResponseEntity<Void> deleteItem(long id);

	
	/**
	 * @ApiImplicitParam入参
	 */
	@ApiOperation(value = "创建项目", notes = "根据POST发送的JSON创建新的项目对象")
	@ApiImplicitParam(name = "item", value = "项目信息", required = true, dataType = "ItemBean")
	public ResponseEntity<Long> createItem(@RequestBody ItemBean item);

	/**
	 * @ApiImplicitParams、@ApiImplicitParam多个入参
	 */
	@ApiOperation(value = "更新项目", notes = "根据id和POST发送的JSON更新项目")
	@ApiImplicitParams({
	     @ApiImplicitParam(paramType="path", name = "id", value = "项目ID", required = true, dataType = "long"),
	     @ApiImplicitParam(paramType="query", name = "item", value = "项目内容", required = true, dataType = "ItemBean")
	})
	public ResponseEntity<Void> updateItem(long id, ItemBean item);

	
}
