package com.revature.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Attribute;
import com.revature.services.AttributeService;

@RestController
@RequestMapping("/attributes")
public class AttributeController {

	private AttributeService attributeService;
	
	@PutMapping
	public Attribute updateAttribute(@RequestBody Attribute updateAttribute) {
		return attributeService.updateAttribute(updateAttribute);
	}
}
