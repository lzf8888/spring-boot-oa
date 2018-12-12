package com.fp.oa.sample.web;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated // use this annotation at controller to activate param validation
public class ValidationExampleController {

	@GetMapping("/validation/{path1}")
	public String validate(@Valid @Min(1) @PathVariable int path1) {
		return "validated " + path1;
	}
}
