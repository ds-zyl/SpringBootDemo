package com.springcloud.study.lesson2.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api4")
public class GetParamDemoController {

	/**
	 * 获取Rest风格的参数 
	 * http://localhost:8080/api4/demo1/1
	 */
	@GetMapping(value = "/demo1/{id}")
	public ResponseEntity<String> demo1(@PathVariable long id) {
		return new ResponseEntity<String>("id:" + id, HttpStatus.OK);
	}

	/**
	 * 获取Rest风格的参数，若方法参数名称和需要绑定的uri template中变量名称不一致，需要在@PathVariable("name")指定uri
	 * template中的名称。 
	 * http://localhost:8080/api4/demo2/1
	 */
	@GetMapping(value = "/demo2/{id}")
	public ResponseEntity<String> demo2(@PathVariable("id") Integer myId) {
		return new ResponseEntity<String>("id:" + myId, HttpStatus.OK);
	}

	/**
	 * 获取路径？后的参数 如果请求参数和处理方法参数的名称一样的话，@RequestParam 注解的 value 这个参数就可省掉了
	 * http://localhost:8080/api4/demo3?id=1&name=zhangsan
	 */
	@RequestMapping(value = "/demo3")
	public ResponseEntity<String> demo3(@RequestParam String id, @RequestParam(value = "name") String myname) {
		return new ResponseEntity<String>("id:" + id + " , name:" + myname, HttpStatus.OK);
	}

	/**
	 * 参数required定义了参数值是否是必须要传的。 /name?person=xyz /name
	 * http://localhost:8080/api4/demo4?person=xyz
	 * http://localhost:8080/api4/demo4
	 */
	@RequestMapping(value = "/demo4")
	public ResponseEntity<String> demo4(@RequestParam(value = "person", required = false) String personName) {
		return new ResponseEntity<String>("name:" + personName, HttpStatus.OK);
	}


	/**
	 * @RequestHeader 注解，可以把Request请求header部分的值绑定到方法的参数上。
	 * 	Host                    localhost:8080
	 * 	Accept                  text/html,application/xhtml+xml,application/xml;q=0.9
	 * 	Accept-Language         fr,en-gb;q=0.7,en;q=0.3
	 * 	Accept-Encoding         gzip,deflate
	 * 	Accept-Charset          ISO-8859-1,utf-8;q=0.7,*;q=0.7
	 * 	Keep-Alive              300
	 * http://localhost:8080/api4/demo5
	 */
	@RequestMapping("/demo5")
	public ResponseEntity<String> demo5(@RequestHeader("Accept-Encoding") String encoding,
			@RequestHeader("Keep-Alive") long keepAlive) {

		return new ResponseEntity<String>("encoding:" + encoding + " , keepAlive:" + keepAlive, HttpStatus.OK);
	}

	/**
	 * @CookieValue 可以把Request header中关于cookie的值绑定到方法的参数上。
	 * JSESSIONID=415A4AC178C59DACE0B2C9CA727CDD84
	 */
	@RequestMapping("/demo6")
	public ResponseEntity<String> demo6(@CookieValue("JSESSIONID") String cookie) {
		return new ResponseEntity<String>("cookie:" + cookie, HttpStatus.OK);
	}
}
