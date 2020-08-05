package doan2020.SportTournamentSupportSystem.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.NotificationConverter;
import doan2020.SportTournamentSupportSystem.response.Response;

@RestController
@CrossOrigin
@RequestMapping("/notification")
public class NotificationAPI {
	
//	@Autowired
//	private NotificationConverter converter;
//	
//	@GetMapping("/getOne")
//	public ResponseEntity<Response> getOneNotification(@RequestParam(value = "id") Long id) {
//		System.out.println("NotificationAPI - getOneNotification");
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//
//		try {
//			System.out.println("NotificationAPI - cp1");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "");
//			System.out.println("NotificationAPI - cp2");
//		} catch (Exception e) {
//			System.out.println("NotificationAPI - exception");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "");
//		}
//
//		System.out.println("NotificationAPI - cp3");
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		System.out.println("NotificationAPI - cp pass");
//		return new ResponseEntity<Response>(response, httpStatus);
//
//	}
//	
//	@GetMapping("/getAll")
//	public ResponseEntity<Response> getAllNotification(@RequestParam(value = "page") Integer page) {
//		System.out.println("NotificationAPI - getAllNotification");
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//
//		try {
//			System.out.println("NotificationAPI - cp1");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "");
//			System.out.println("NotificationAPI - cp2");
//		} catch (Exception e) {
//			System.out.println("CompetitionSettingAPI - exception");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "");
//		}
//
//		System.out.println("NotificationAPI - cp3");
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		System.out.println("NotificationAPI - cp pass");
//		return new ResponseEntity<Response>(response, httpStatus);
//
//	}
//	
//	@PostMapping("")
//	public ResponseEntity<Response> createNotification(@RequestBody NotificationDTO notificationDTO) {
//		System.out.println("NotificationAPI - createNotification");
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//
//		try {
//			System.out.println("NotificationAPI - cp1");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "");
//			System.out.println("NotificationAPI - cp2");
//		} catch (Exception e) {
//			System.out.println("NotificationAPI - exception");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "");
//		}
//
//		System.out.println("NotificationAPI - cp3");
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		System.out.println("NotificationAPI - cp pass");
//		return new ResponseEntity<Response>(response, httpStatus);
//
//	}
//	
//	@PutMapping("")
//	public ResponseEntity<Response> editNotification(@RequestParam(value = "id") Long id) {
//		System.out.println("NotificationAPI - editNotification");
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//
//		try {
//			System.out.println("NotificationAPI - cp1");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "");
//			System.out.println("NotificationAPI - cp2");
//		} catch (Exception e) {
//			System.out.println("NotificationAPI - exception");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "");
//		}
//
//		System.out.println("NotificationAPI - cp3");
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		System.out.println("NotificationAPI - cp pass");
//		return new ResponseEntity<Response>(response, httpStatus);
//
//	}
//	
//	@PutMapping("")
//	public ResponseEntity<Response> deleteNotification(@RequestParam(value = "id") Long id) {
//		System.out.println("NotificationAPI - deleteNotification");
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//
//		try {
//			System.out.println("NotificationAPI - cp1");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "");
//			System.out.println("NotificationAPI - cp2");
//		} catch (Exception e) {
//			System.out.println("NotificationAPI - exception");
//			result.put("Notification", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "");
//		}
//
//		System.out.println("NotificationAPI - cp3");
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		System.out.println("NotificationAPI - cp pass");
//		return new ResponseEntity<Response>(response, httpStatus);
//
//	}

}
