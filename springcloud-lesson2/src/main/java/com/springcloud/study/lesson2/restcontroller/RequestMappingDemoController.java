package com.springcloud.study.lesson2.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RequestMapping使用方法样例demo
 */
@RestController
@RequestMapping("/api3")
public class RequestMappingDemoController {
	/**
	 * @RequestMapping value="方法名" http://localhost:8080/api3/demo1
	 */
	@RequestMapping(value = "/demo1")
	public ResponseEntity<String> demo1() {
		System.out.println("@RequestMapping value=\"方法名\"");
		return new ResponseEntity<String>("/demo1" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping 省略value，直接写"方法名" http://localhost:8080/api3/demo2
	 */
	@RequestMapping("/demo2")
	public ResponseEntity<String> demo2() {
		System.out.println("@RequestMapping 省略value，直接写\"方法名\"");
		return new ResponseEntity<String>("/demo2" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping 省略value和\"方法名\" 
	 */
//	@RequestMapping
//	public ResponseEntity<String> demo3() {
//		System.out.println("@RequestMapping 省略value和\"方法名\"");
//		return new ResponseEntity<String>("/demo3" , HttpStatus.OK);
//	}

	/**
	 * @RequestMapping value有多个值 
	 * 				   http://localhost:8080/api3/demo4
	 *                 http://localhost:8080/api3/demo4abc
	 */
	@RequestMapping(value = { "/demo4", "/demo4abc" })
	public ResponseEntity<String> demo4() {
		System.out.println("@RequestMapping value={\"/demo4\",\"/demo4abc\"}");
		return new ResponseEntity<String>("/demo4, /demo4abc" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping value 带有模糊匹配的多个URI 
	 * 				   http://localhost:8080/api3/demo5
	 *                 http://localhost:8080/api3/pagedemo5
	 */
	@RequestMapping(value = { "", "/demo5", "/page", "page*", "view/*,**/msg" })
	public ResponseEntity<String> demo5() {
		System.out.println("@RequestMapping value={\"\",\"/page\",\"page*\",\"view/*,**/msg\"}");
		return new ResponseEntity<String>("/demo5" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping value中也可通过正则表达式表示一类值 
	 * 				   http://localhost:8080/api3/demo6/demo
	 */
	@RequestMapping(value = { "/demo6/{demo:[a-z-]+}-{version:\\d\\.\\d\\.\\d}.{extension:\\.[a-z]}" })
	public ResponseEntity<String> demo6() {
		System.out.println(
				"@RequestMapping value = {\"/demo6/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}.{extension:\\.[a-z]}\"}");
		return new ResponseEntity<String>("/demo6" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping consumes 指定处理请求的提交内容类型（Content-Type），例如application/json,
	 *                 text/html 方法仅处理request Content-Type为“application/json”类型的请求。
	 *                 http://localhost:8080/api3/demo7
	 */
	@RequestMapping(value = "/demo7", consumes = "application/json")
	public ResponseEntity<String> demo7() {
		System.out.println(
				"@RequestMapping(value = \"/demo7\", method = RequestMethod.POST, consumes=\"application/json\")");
		return new ResponseEntity<String>("/demo7" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping consumes配置多个。
	 * 				   http://localhost:8080/api3/demo8
	 */
	@RequestMapping(value = "/demo8", consumes = { "application/JSON", "application/XML" })
	public ResponseEntity<String> demo8() {
		System.out
				.println("@RequestMapping(value = \"/demo8\", consumes = {\"application/JSON\",\"application/XML\"})");
		return new ResponseEntity<String>("/demo8" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
	 */
	@RequestMapping(value = "/demo9", produces = "application/json")
	public ResponseEntity<String> demo9() {
		System.out.println(
				"@RequestMapping(value = \"/demo9/{petId}\", method = RequestMethod.GET, produces=\"application/json\")");
		return new ResponseEntity<String>("/demo9" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping 方法仅处理request请求中Accept头中包含了”application/json”的请求，同时暗示了返回的内容类型为application/json;
	 */
	@RequestMapping(value = "/demo10", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> demo10() {
		System.out.println("@RequestMapping(value = \"/demo10\",produces = \"text/plain;charset=UTF-8\")");
		return new ResponseEntity<String>("/demo10" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping params={"personId=11"} 是 URI中personId=11的时候, demo11() 会执行，因为
	 *                 id 的值是11 
	 *                 http://localhost:8080/api3/demo11?personId=11
	 */
	@RequestMapping(value = "/demo11", params = { "personId=11" })
	public ResponseEntity<String> demo11(@RequestParam("personId") String id) {
		System.out.println("@RequestMapping(value = \"/demo11\", params = {\"personId=11\"})");
		return new ResponseEntity<String>("/demo11?personId=11" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping params={"personId=11"} 是 URI中personId=12的时候, demo12() 会执行，因为
	 *                 id 的值是12 
	 *                 http://localhost:8080/api3/demo11?personId=12
	 */
	@RequestMapping(value = "/demo11", params = { "personId=12" })
	public ResponseEntity<String> demo12(@RequestParam("personId") String id) {
		System.out.println("@RequestMapping(value = \"/demo11\", params = {\"personId=12\"})");
		return new ResponseEntity<String>("/demo11?personId=12" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping headers指定request中必须包含某些指定的值，才能让该方法处理请求。
	 *                 http://localhost:8080/api3/demo13
	 */
	@RequestMapping(value = "/demo13", headers = "Referer=http://www.ifeng.com/")
	public ResponseEntity<String> demo13() {
		System.out.println(
				"@RequestMapping(value = \"/demo13\", method = RequestMethod.GET, headers=\"Referer=http://www.ifeng.com/\")");
		return new ResponseEntity<String>("/demo13" , HttpStatus.OK);
	}

	/**
	 * @RequestMapping headers中包含多个值
	 * 				   http://localhost:8080/api3/demo14
	 */
	@RequestMapping(value = "/demo14", headers = { "content-type=text/plain", "content-type=text/html" })
	public ResponseEntity<String> demo14() {
		System.out.println(
				"@RequestMapping(value = \"/demo14\", headers = { \"content-type=text/plain\", \"content-type=text/html\" })");
		return new ResponseEntity<String>("/demo14" , HttpStatus.OK);
	}
}
