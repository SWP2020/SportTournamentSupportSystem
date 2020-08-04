package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.CommentConverter;
import doan2020.SportTournamentSupportSystem.dtIn.CommentDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.CommentDtOut;
import doan2020.SportTournamentSupportSystem.entity.CommentEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICommentService;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentAPI {
	@Autowired
	private ICommentService CommentService;
	
	@Autowired
	private CommentConverter converter;
	
	/* ----------------Get One Comment ------------------------ */
	@GetMapping("/getOne")
	public ResponseEntity<Response> GetComment(@RequestParam("id") Long id) {
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CommentEntity commentEntity = new CommentEntity();
		CommentDtOut commentDtOut = new CommentDtOut();
		try {
			commentEntity = CommentService.findOneById(id);

			if (commentEntity == null) {
				result.put("commentEntity", null);
				config.put("global", 0);
				error.put("messageCode", 1);
				error.put("message", "commentEntity is not exist");
				response.setConfig(config);
				response.setResult(result);
				response.setError(error);
				return new ResponseEntity<Response>(response, httpStatus);
			}
			
			commentDtOut = converter.toDTO(commentEntity);

			result.put("Comment", commentDtOut);

			error.put("messageCode", 0);
			error.put("message", "get Comment Successfully");

			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * ---------------- find all Comment by PostId
	 * ------------------------
	 */
	@GetMapping("/getAllByPostId")
	public ResponseEntity<Response> getCommentByPostId(@RequestParam("id") Long id) {
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<CommentEntity> commentEntitys = new ArrayList<CommentEntity>();
		List<CommentDtOut> commentDtOuts = new ArrayList<CommentDtOut>();

		if (id == null) {
			result.put("Comment", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required Comment's id!");

			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		commentEntitys = CommentService.findByPostId(id);
		
		if (commentEntitys.isEmpty()) {
			result.put("Comment", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Comment is not exist");
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}
		
		try {
			for (CommentEntity commentEntity : commentEntitys) {
				CommentDtOut commentDtOut = converter.toDTO(commentEntity);
				commentDtOuts.add(commentDtOut);
			}
			
			result.put("list Comment", commentDtOuts);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

			System.out.println("true");

		} catch (Exception e) {
			result.put("Comment", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "commentDtOuts is not exist");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/*
	 * ---------------- find all Comment by AuthorId
	 * ------------------------
	 */
	@GetMapping("/getAllByAuthorId")
	public ResponseEntity<Response> getCommentByAuthorId(@RequestParam("id") Long id) {
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<CommentEntity> commentEntitys = new ArrayList<CommentEntity>();
		List<CommentDtOut> commentDtOuts = new ArrayList<CommentDtOut>();

		if (id == null) {
			result.put("Comment", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required Comment's id!");

			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		commentEntitys = CommentService.findByAuthorId(id);
		
		if (commentEntitys.isEmpty()) {
			result.put("Comment", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Comment is not exist");
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}
		
		try {
			for (CommentEntity commentEntity : commentEntitys) {
				CommentDtOut commentDtOut = converter.toDTO(commentEntity);
				commentDtOuts.add(commentDtOut);
			}
			
			result.put("list Comment", commentDtOuts);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

			System.out.println("true");

		} catch (Exception e) {
			result.put("Comment", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "commentDtOuts is not exist");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- add new Comment ------------------------ */
	@PostMapping
	public ResponseEntity<Response> createComment(@RequestBody CommentDtIn commentDtIn) {
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CommentEntity commentEntity = new CommentEntity();

		try {
			commentEntity = converter.toEntity(commentDtIn);

			CommentService.addComment(commentEntity);

			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Add new Comment successfull");

			System.out.println("true");

		} catch (Exception e) {

		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- Edit Comment ------------------------ */
	@PutMapping()
	public ResponseEntity<Response> editComment(@RequestParam("id") Long id,
			@RequestBody CommentDtIn commentDtIn) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			CommentEntity commentEntity = new CommentEntity();
			CommentEntity oldCommentEntity = new CommentEntity();
			if (id != null) {
				oldCommentEntity = CommentService.findOneById(id);
				if (oldCommentEntity != null) {
					commentEntity = converter.toEntity(commentDtIn, oldCommentEntity);
					CommentService.editComment(commentEntity);
					httpStatus = HttpStatus.OK;
					error.put("messageCode", 0);
					error.put("message", "Edit Comment Successfull");
				} else {
					httpStatus = HttpStatus.OK;
					error.put("messageCode", 1);
					error.put("message", "Not Find Comment to update ");
				}
			} else {
				httpStatus = HttpStatus.OK;
				error.put("messageCode", 1);
				error.put("message", "Comment is not enter");
			}
		} catch (Exception ex) {
			httpStatus = HttpStatus.OK;
			error.put("messageCode", 1);
			error.put("message", "Edit Comment fails");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* delete one comment */
	@DeleteMapping("")
	public ResponseEntity<Response> deleteComment(@RequestParam("id") Long id) {
		Response response = new Response();
		HttpStatus httpStatus = null;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CommentEntity commentEntity = new CommentEntity();
		try {
			if (id != null) {
				commentEntity = CommentService.findOneById(id);
				if (commentEntity != null) {
					CommentService.deleteComment(commentEntity);
					httpStatus = HttpStatus.OK;
					error.put("messageCode", 0);
					error.put("message", "Delete Comment Successfull");
				} else {
					httpStatus = HttpStatus.OK;
					error.put("messageCode", 1);
					error.put("message", "Not Find Comment to delete ");
				}
			} else {
				httpStatus = HttpStatus.OK;
				error.put("messageCode", 1);
				error.put("message", "Comment is not enter");
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.OK;
			error.put("messageCode", 1);
			error.put("message", "delete Comment fails");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
