package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.CommentConverter;
import doan2020.SportTournamentSupportSystem.dto.CommentDTO;
import doan2020.SportTournamentSupportSystem.entity.CommentEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICommentService;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentsAPI {
	
	@Autowired
	private CommentConverter converter;
	
	@Autowired
	private ICommentService service;
	
	@GetMapping("")
	public ResponseEntity<Response> getAll(){
		System.out.println("CommentsAPI: getAll: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<CommentEntity> entities = new ArrayList<CommentEntity>();
		List<CommentDTO> dtos = new ArrayList<CommentDTO>();
		try {
					
				entities = (List<CommentEntity>) service.findAll();
				
				if (entities == null) { // not found
					result.put("Comment", dtos);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					for (CommentEntity entity: entities) {
						CommentDTO dto = converter.toDTO(entity);
						dtos.add(dto);
					} 
					
					result.put("Comment", dtos);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			
			System.out.println("CommentsAPI: getAll: no exception");
		} catch (Exception e) {
			System.out.println("CommentsAPI: getAll: has exception");
			result.put("Comment", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CommentsAPI: getAll: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@GetMapping("/getByPostId")
	public ResponseEntity<Response> getByPostId(@RequestParam(value = "postId", required = false) Long postId) {
		System.out.println("CommentsAPI: getByPostId: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<CommentEntity> entities = new ArrayList<CommentEntity>();
		List<CommentDTO> dtos = new ArrayList<CommentDTO>();
		try {
			if (postId == null) { // id null
				result.put("Comment", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param postId");
			} else { // id not null
				
				entities = (List<CommentEntity>) service.findByPostId(postId);
				
				if (entities == null) { // not found
					result.put("Comment", dtos);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					for (CommentEntity entity: entities) {
						CommentDTO dto = converter.toDTO(entity);
						dtos.add(dto);
					} 
					
					result.put("Comment", dtos);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("CommentsAPI: getByPostId: no exception");
		} catch (Exception e) {
			System.out.println("CommentsAPI: getByPostId: has exception");
			result.put("Comment", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CommentsAPI: getByPostId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<Response> getByUserId(@RequestParam(value = "userId", required = false) Long userId) {
		System.out.println("CommentsAPI: getByPostId: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<CommentEntity> entities = new ArrayList<CommentEntity>();
		List<CommentDTO> dtos = new ArrayList<CommentDTO>();
		try {
			if (userId == null) { // id null
				result.put("Comment", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param userId");
			} else { // id not null
				
				entities = (List<CommentEntity>) service.findByUserId(userId);
				
				if (entities == null) { // not found
					result.put("Comment", dtos);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					for (CommentEntity entity: entities) {
						CommentDTO dto = converter.toDTO(entity);
						dtos.add(dto);
					} 
					
					result.put("Comment", dtos);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("CommentsAPI: getByUserId: no exception");
		} catch (Exception e) {
			System.out.println("CommentsAPI: getByUserId: has exception");
			result.put("Comment", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CommentsAPI: getByUserId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
