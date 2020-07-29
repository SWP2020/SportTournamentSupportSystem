package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import doan2020.SportTournamentSupportSystem.converter.PostConverter;
import doan2020.SportTournamentSupportSystem.dtOut.PostDtOut;
import doan2020.SportTournamentSupportSystem.entity.PostEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IPostService;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostAPI {
	@Autowired
	private IPostService service;

	@Autowired
	private PostConverter converter;

	/*
	 * Tim kiem post theo paging
	 */
	@GetMapping("/getAllByTournamentId")
	public ResponseEntity<Response> getPostPagingByTournamentId(@RequestParam(value = "page") Integer page, @RequestParam(value = "id") Long id) {
		System.out.println("getPost");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<PostDtOut> postDtOuts = new ArrayList<PostDtOut>();
		List<PostEntity> list = new ArrayList<PostEntity>();
//		System.out.println("2");

		if (page == null || id == null) {
			result.put("Post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required Post's page!");
			httpStatus = HttpStatus.OK;
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		Sort sortable = Sort.by("id").ascending();
		int limit = 3;
		Pageable pageable = PageRequest.of(page - 1, limit, sortable);

		list = (List<PostEntity>) service.findByTournamentId(id, pageable);

		if (list.isEmpty()) {
			result.put("Post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Post is not exist");
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		try {
			for (PostEntity postEntity : list) {

				PostDtOut resDTO = converter.toDTO(postEntity);
				postDtOuts.add(resDTO);
				
			}

			result.put("list post", postDtOuts);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

			System.out.println("true");

		} catch (Exception e) {
			result.put("Post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Error to get list Post");
			System.out.println(e.getMessage().toString());
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);

		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/*
	 * Tim kiem post theo paging
	 */
	@GetMapping("/getAll")
	public ResponseEntity<Response> getPostPaging(@RequestParam(value = "page") Integer page) {
		System.out.println("getPost");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<PostDtOut> postDtOuts = new ArrayList<PostDtOut>();
		List<PostEntity> list = new ArrayList<PostEntity>();
//		System.out.println("2");

		if (page == null) {
			result.put("Post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required Post's page!");
			httpStatus = HttpStatus.OK;
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		Sort sortable = Sort.by("id").ascending();
		int limit = 3;
		Pageable pageable = PageRequest.of(page - 1, limit, sortable);

		list = (List<PostEntity>) service.findAll(pageable);

		if (list.isEmpty()) {
			result.put("Post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Post is not exist");
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		try {
			for (PostEntity postEntity : list) {

				PostDtOut resDTO = converter.toDTO(postEntity);
				postDtOuts.add(resDTO);
				
			}

			result.put("list post", postDtOuts);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

			System.out.println("true");

		} catch (Exception e) {
			result.put("Post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Error to get list Post");
			System.out.println(e.getMessage().toString());
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);

		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Get post theo id
	 */
	@GetMapping("/getOne")
	public ResponseEntity<Response> getPost(@RequestParam(value = "id", required = true) Long id) {
		System.out.println("getPost");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		if (id == null) {
			result.put("post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required post id");
			httpStatus = HttpStatus.OK;
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		PostEntity res;

		res = service.findById(id);

		try {
			PostDtOut resDTO = converter.toDTO(res);

			result.put("post", resDTO);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

		} catch (Exception e) {
			result.put("post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Error to get list Post");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);

		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Post
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createPost(@RequestBody Map<String, Object> newPost) {
		System.out.println("createPost");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			PostEntity postEntity = converter.toEntity(newPost);
			System.out.println("convert OK");
			service.addOne(postEntity);
			System.out.println("add OK");
			PostDtOut dto = converter.toDTO(postEntity);
			System.out.println("convert OK");
			result.put("post", dto);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Post create successfuly");
		} catch (Exception e) {
			result.put("post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Post create fail");
		} 

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot Post
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editPost(@RequestBody Map<String, Object> post, @RequestParam Long id) {
		System.out.println("editPost");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			PostEntity postEntity = converter.toEntity(post);
			System.out.println("convert OK");
			PostEntity newPost = service.update(id, postEntity);
			System.out.println("add OK");
			PostDtOut dto = converter.toDTO(newPost);
			System.out.println("convert OK");
			result.put("post", dto);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Post update successfuly");
		} catch (Exception e) {
			result.put("post", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Post update fail");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
