package com.app.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.Model.AiRequest;
import com.app.Model.AiResponse;
import com.app.Model.Lang;

@Service
public class AiService {
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.url}")
	private String url;
	
	
	@Autowired
	private RestTemplate template;
	
	public String convertCode(Lang code,String lang) {
		
		AiRequest request = new AiRequest(model, "Translate the following code to "+lang +" Code-> [ "+code+" ] . Provide just the code dont provide my extra text instead of code without any explaination");
		AiResponse response = template.postForObject(url, request, AiResponse.class);
		String res = response.getChoices().get(0).getMessage().getContent();
		return res;
	}
	
	public String debugCode(Lang code) {
		AiRequest request = new AiRequest(model, "Please analyze the following code and identify any issues or errors. Provide a detailed explanation of each issue you find and suggest corrections for each one. If you encounter any code that appears to be unclear or poorly written but not necessarily erroneous, please offer suggestions for improvement as well. Please make sure to maintain the original structure and logic of the code while fixing the issues. Here is the code to debug: "+code);
		AiResponse response = template.postForObject(url, request, AiResponse.class);
		String res = response.getChoices().get(0).getMessage().getContent();
		return res;
	}
	
	public String qualityCheck(Lang code) {
		AiRequest request = new AiRequest(model, "Please provide a code quality assessment for the given code. Consider the following parameters:\r\n"
				+ "\r\n"
				+ "			1. Code Consistency: Evaluate the code for consistent coding style, naming conventions, and formatting.\r\n"
				+ "			2. Code Performance: Assess the code for efficient algorithms, optimized data structures, and overall performance considerations.\r\n"
				+ "			3. Code Documentation: Review the code for appropriate comments, inline documentation, and clear explanations of complex logic.\r\n"
				+ "			4. Error Handling: Examine the code for proper error handling and graceful error recovery mechanisms.\r\n"
				+ "			5. Code Testability: Evaluate the code for ease of unit testing, mocking, and overall testability.\r\n"
				+ "			6. Code Modularity: Assess the code for modular design, separation of concerns, and reusability of components.\r\n"
				+ "			7. Code Complexity: Analyze the code for excessive complexity, convoluted logic, and potential code smells.\r\n"
				+ "			8. Code Duplication: Identify any code duplication and assess its impact on maintainability and readability.\r\n"
				+ "			9. Code Readability: Evaluate the code for readability, clarity, and adherence to coding best practices.\r\n"
				+ "\r\n"
				+ "			Please provide a summary of the code quality assessment and a report showing the percentage-wise evaluation for each parameter mentioned above.\r\n Code-> "+code);
		AiResponse response = template.postForObject(url, request, AiResponse.class);
		String res = response.getChoices().get(0).getMessage().getContent();
		return res;
	}
	
	
}
