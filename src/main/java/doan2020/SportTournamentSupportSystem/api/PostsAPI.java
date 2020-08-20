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
@RequestMapping("/posts")
public class PostsAPI {
	@Autowired
	private IPostService service;

	@Autowired
	private PostConverter converter;

	/*
	 * Find posts theo paging
	 */
	@GetMapping("")
	public ResponseEntity<Response> getPosts(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("PostsAPI: getPosts: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<PostDTO> postDTOs = new ArrayList<PostDTO>();
		List<PostEntity> list = new ArrayList<PostEntity>();
		try {

			if (limit == null || limit <= 0)
				limit = 3;

			if (page == null || page <= 0)
				page = 1;

			Sort sortable = Sort.by("id").ascending();
			Pageable pageable = PageRequest.of(page - 1, limit, sortable);

			list = (List<PostEntity>) service.findAll();

			if (list.isEmpty()) {// list is not exist
				result.put("Total page", null);
				result.put("Posts", null);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Page Posts is not exist");

			} else {// list is exist

				for (PostEntity postEntity : list) {

					PostDTO resDTO = converter.toDTO(postEntity);
					postDTOs.add(resDTO);
				}

				List<PostEntity> postEntities = (List<PostEntity>) service.findAll();

				int total = postEntities.size();
				int totalPage = total / limit;
				if (total % limit != 0) {
					totalPage++;
				}
				result.put("Total page", totalPage);
				result.put("Posts", postDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "get Page Posts successfully");

				System.out.println("PostsAPI: getPosts: no exception");
			}
		} catch (Exception e) {
			System.out.println("PostsAPI: getPosts: has exception");
			result.put("Posts", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("PostsAPI: getPosts: pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Find posts by TournamentId
	 */
	@GetMapping("/getByUserId")
	public ResponseEntity<Response> getPostByUserId(@RequestParam(value = "page") Integer page,
			@RequestParam(value = "limit") Integer limit, @RequestParam(value = "userId") Long userId) {
		System.out.println("PostsAPI: getPostByTournamentId: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<PostDTO> postDTOs = new ArrayList<PostDTO>();
		List<PostEntity> list = new ArrayList<PostEntity>();
		try {

			if (limit == null || limit <= 0)
				limit = 3;

			if (page == null || page <= 0)
				page = 1;

			Sort sortable = Sort.by("id").ascending();
			Pageable pageable = PageRequest.of(page - 1, limit, sortable);

			list = (List<PostEntity>) service.findAll();

			if (list.isEmpty()) {// list is not exist
				result.put("Total page", null);
				result.put("Posts", null);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Page Posts is not exist");

			} else {// list is exist

				for (PostEntity postEntity : list) {

					PostDTO resDTO = converter.toDTO(postEntity);
					postDTOs.add(resDTO);
				}

				List<PostEntity> postEntities = (List<PostEntity>) service.findByUserId(pageable, userId);

				int total = postEntities.size();
				int totalPage = total / limit;
				if (total % limit != 0) {
					totalPage++;
				}
				result.put("Total page", totalPage);
				result.put("Posts", postDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "get Page Posts successfully");

				System.out.println("PostsAPI: getPosts: no exception");
			}
		} catch (Exception e) {
			System.out.println("PostsAPI: getPosts: has exception");
			result.put("Posts", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("PostsAPI: getPosts: pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Find posts by TournamentId
	 */
	@GetMapping("/getByTournamentId")
	public ResponseEntity<Response> getPostByTournamentId(@RequestParam(value = "page") Integer page,
			@RequestParam(value = "limit") Integer limit, @RequestParam(value = "tournamentId") Long tournamentId) {
		System.out.println("PostsAPI: getPostByTournamentId: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<PostDTO> postDTOs = new ArrayList<PostDTO>();
		List<PostEntity> list = new ArrayList<PostEntity>();
		try {

			if (limit == null || limit <= 0)
				limit = 3;

			if (page == null || page <= 0)
				page = 1;

			Sort sortable = Sort.by("id").ascending();
			Pageable pageable = PageRequest.of(page - 1, limit, sortable);

			list = (List<PostEntity>) service.findAll();

			if (list.isEmpty()) {// list is not exist
				result.put("Total page", null);
				result.put("Posts", null);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Page Posts is not exist");

			} else {// list is exist

				for (PostEntity postEntity : list) {

					PostDTO resDTO = converter.toDTO(postEntity);
					postDTOs.add(resDTO);
				}

				List<PostEntity> postEntities = (List<PostEntity>) service.findByTournamentId(pageable, tournamentId);

				int total = postEntities.size();
				int totalPage = total / limit;
				if (total % limit != 0) {
					totalPage++;
				}
				result.put("Total page", totalPage);
				result.put("Posts", postDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "get Page Posts successfully");

				System.out.println("PostsAPI: getPosts: no exception");
			}
		} catch (Exception e) {
			System.out.println("PostsAPI: getPosts: has exception");
			result.put("Posts", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("PostsAPI: getPosts: pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
