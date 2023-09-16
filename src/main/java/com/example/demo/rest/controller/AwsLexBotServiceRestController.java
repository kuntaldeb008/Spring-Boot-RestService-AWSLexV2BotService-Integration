package com.example.demo.rest.controller;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lexruntimev2.LexRuntimeV2Client;
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextRequest;
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextResponse;
import org.json.JSONObject;

@RestController
@RequestMapping(path = "/getawslexbotresponse")
public class AwsLexBotServiceRestController {
	
	private String sessionId;
	@PostConstruct
    public void init() {
      sessionId = UUID.randomUUID().toString();
    }

	 @GetMapping(value = "/{userInput}", produces = "application/json")
	 public ResponseEntity<String> getBotResponse(@PathVariable("userInput") String userInput) {
	        
	        String botId = "";
	        String botAliasId = "";
	        String localeId = "en_US";
	        String accessKey = "";
	        String secretKey = "";
	        
	        Region region = Region.US_EAST_1; // pick an appropriate region

	        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
	        AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider.create(awsCreds);

	        LexRuntimeV2Client lexV2Client = LexRuntimeV2Client
	                .builder()
	                .credentialsProvider(awsCredentialsProvider)
	                .region(region)
	                .build();

	        // utterance 1
	        RecognizeTextRequest recognizeTextRequest = getRecognizeTextRequest(botId, botAliasId, localeId, sessionId, userInput);
	        RecognizeTextResponse recognizeTextResponse = lexV2Client.recognizeText(recognizeTextRequest);

	        StringBuilder respBuilder = new StringBuilder();
	        recognizeTextResponse.messages().forEach(message -> {
	            
	        	respBuilder.append(message.content());
	        });
	        //String x = "{\"botresponse\" : \""+respBuilder.toString()+"\"}";
	        
	     String resp = respBuilder.toString();
	     JSONObject jsonObject = new JSONObject();
	     jsonObject.put("botresponse", resp);
	     String jsonString = jsonObject.toString();

	     System.out.println(jsonString);
	     
	     return new ResponseEntity<>(jsonString, HttpStatus.OK);

		/* return new ResponseEntity<>("{\"botresponse\" : \""+respBuilder.toString()+"\"}", HttpStatus.OK); */
	 }
		
	 private static RecognizeTextRequest getRecognizeTextRequest(String botId, String botAliasId, String localeId, String sessionId, String userInput) {
	        RecognizeTextRequest recognizeTextRequest = RecognizeTextRequest.builder()
	                .botAliasId(botAliasId)
	                .botId(botId)
	                .localeId(localeId)
	                .sessionId(sessionId)
	                .text(userInput)
	                .build();
	        return recognizeTextRequest;
	    }
}
