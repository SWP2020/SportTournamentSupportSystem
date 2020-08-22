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

import doan2020.SportTournamentSupportSystem.converter.CommentConverter;
import doan2020.SportTournamentSupportSystem.dto.CommentDTO;
import doan2020.SportTournamentSupportSystem.entity.CommentEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICommentService;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentAPI {
	
	@Autowired
	private CommentConverter converter;
	
	@Autowired
	private ICommentService service;
	
	
	@GetMapping("")
	public ResponseEntity<Response> getComment(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("CommentAPI: getComment: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CommentEntity commentEntity = new CommentEntity();
		CommentDTO commentDTO = new CommentDTO();
		try {
			if (id == null) { // id null
				result.put("Comment", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				commentEntity = service.findOneById(id);
				
				if (commentEntity == null) { // not found
					result.put("Comment", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					commentDTO = converter.toDTO(commentEntity);
					
					result.put("Comment", commentDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("CommentAPI: getComment: no exception");
		} catch (Exception e) {
			System.out.println("CommentAPI: getComment: has exception");
			result.put("Comment", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CommentAPI: getComment: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Comment
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createComment(@RequestBody CommentDTO newComment) {
		System.out.println("CommentAPI: createComment: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CommentEntity commentEntity = new CommentEntity();
		
		try {
			commentEntity = converter.toEntity(newComment);
			
			commentEntity = service.create(commentEntity);
			
			CommentDTO dto = converter.toDTO(commentEntity);

			result.put("Comment", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Comment create successfuly");
			System.out.println("CommentAPI: createComment: no exception");
		} catch (Exception e) {
			System.out.println("CommentAPI: createComment: has exception");
			result.put("Comment", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CommentAPI: createComment: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot Comment
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editComment(
			@RequestBody CommentDTO comment,
			@RequestParam Long id) {
		System.out.println("CommentAPI: editComment: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CommentEntity commentEntity = new CommentEntity();
		
		try {
			commentEntity = converter.toEntity(comment);
			
			commentEntity = service.update(id, commentEntity);
			
			CommentDTO dto = converter.toDTO(commentEntity);

			result.put("Comment", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Comment update successfuly");
			System.out.println("CommentAPI: editComment: no exception");
		} catch (Exception e) {
			System.out.println("CommentAPI: editComment: has exception");
			result.put("Comment", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CommentAPI: editComment: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
