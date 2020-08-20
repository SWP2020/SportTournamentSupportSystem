package doan2020.SportTournamentSupportSystem.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.PostConverter;
import doan2020.SportTournamentSupportSystem.dto.PostDTO;
import doan2020.SportTournamentSupportSystem.entity.PostEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IPostService;

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostAPI {
	@Autowired
	private IPostService service;

	@Autowired
	private PostConverter converter;



	/*
	 * Get post theo id
	 */
	@GetMapping("")
	public ResponseEntity<Response> getPost(@RequestParam(value = "id", required = true) Long id) {
		System.out.println("PostAPI - getOnePost - start");
		System.out.println(id);
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		PostEntity postEntity = new PostEntity();
		PostDTO dto = new PostDTO();
		try {

			if (id == null) {// id not exist
				System.out.println("PostAPI - getOnePost - cp1");
				result.put("Post", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Requried id");
			} else {// id exist
				postEntity = service.findOneById(id);
				System.out.println("PostAPI - getOnePost - cp1");
				if (postEntity == null) {// competition is not exist
					System.out.println("PostAPI - getOnePost - cp2");
					result.put("Post", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Post is not exist");
				} else {// competition is exist
					System.out.println("PostAPI - getOnePost - cp3");
					dto = converter.toDTO(postEntity);
					System.out.println("PostAPI - getOnePost - cp4");
					result.put("Post", dto);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "get Post Successfully");
				}
			}

		} catch (Exception e) {
			System.out.println("PostAPI - getOnePost - cp5");
			result.put("Post", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "exception");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PostAPI - getOnePost - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Post
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createPost(@RequestBody PostDTO postDTO) {
		System.out.println("PostAPI - createPost - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		PostEntity postEntity = new PostEntity();

		try {
			postEntity = converter.toEntity(postDTO);

			if (postEntity == null) {// convert false
				System.out.println("PostAPI - createPost - cp1");
				result.put("Post", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "create new Post fail");
			} else {// convert ok
				System.out.println("PostAPI - createPost - cp2");
				service.create(postEntity);

				result.put("Post", postEntity);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "create new Post successfull");
				System.out.println("PostAPI - createPost - cp3");
			}

		} catch (Exception e) {
			System.out.println("PostAPI - createPost - exception");
			result.put("Post", postEntity);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PostAPI - createPost - pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit one Post
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editPost(@RequestBody PostDTO postDTO, @RequestParam Long id) {
		System.out.println("PostAPI - editPost - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		PostEntity postEntity = new PostEntity();

		try {

			if (id == null) {// id is not exist
				System.out.println("PostAPI - editPost - cp1");
				result.put("Post", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required Id");
			} else {// id is exist

				postEntity = converter.toEntity(postDTO);

				if (postEntity == null) {// convert false
					System.out.println("PostAPI - editPost - cp2");
					result.put("Post", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "edit new Post fail");
				} else {// convert ok
					System.out.println("PostAPI - editPost - cp3");
					service.update(id, postEntity);

					result.put("Post", postEntity);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "edit new Post successfull");
					System.out.println("PostAPI - editPost - cp4");
				}
			}
			System.out.println("PostAPI - editPost - has no exception");
		} catch (Exception e) {
			System.out.println("PostAPI - editPost - has exception");
			result.put("Post", postEntity);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PostAPI - editPost - pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/* delete one Post */
	@DeleteMapping("")
	public ResponseEntity<Response> deletePost(@RequestParam("id") Long id) {
		System.out.println("PostAPI - deletePost - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		PostEntity postEntity = new PostEntity();

		try {

			if (id == null) {// id is not exist
				System.out.println("PostAPI - deletePost - cp1");
				result.put("Post", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required Id");
			} else {// id is exist
				service.delete(id);

				result.put("Post", postEntity);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Delete Post successfull");
				System.out.println("PostAPI - deletePost - cp2");
			}

			System.out.println("PostAPI - deletePost - has no exception");
		} catch (Exception e) {
			System.out.println("PostAPI - deletePost - has exception");
			result.put("Post", postEntity);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PostAPI - deletePost - pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
