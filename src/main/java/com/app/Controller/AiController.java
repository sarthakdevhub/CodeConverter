package com.app.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Model.Lang;
import com.app.Service.AiService;

@RestController
@RequestMapping("/bot")
@CrossOrigin(origins = "*")
public class AiController {
	
	@Autowired
	private AiService aiService;

	
	@PostMapping("/convert/{lang}")
	public ResponseEntity<String> convert(@RequestBody Lang code,@PathVariable String lang) {
		return new ResponseEntity<String>(aiService.convertCode(code,lang),HttpStatus.OK);
	}
	
	@PostMapping("/debug")
	public ResponseEntity<String> debug(@RequestBody Lang code) {
		return new ResponseEntity<String>(aiService.debugCode(code),HttpStatus.OK);
	}
	
	@PostMapping("/quality")
	public ResponseEntity<String> qualityCheck(@RequestBody Lang code) {
		return new ResponseEntity<String>(aiService.qualityCheck(code),HttpStatus.OK);
	}
}
