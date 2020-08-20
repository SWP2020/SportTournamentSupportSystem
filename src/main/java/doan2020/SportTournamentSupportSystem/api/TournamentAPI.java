package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.converter.SportConverter;
import doan2020.SportTournamentSupportSystem.converter.TournamentConverter;
import doan2020.SportTournamentSupportSystem.dto.PermissionDTO;
import doan2020.SportTournamentSupportSystem.dto.TournamentDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.PermissionEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IPermissionService;
import doan2020.SportTournamentSupportSystem.service.ISportService;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.impl.FileStorageService;
import doan2020.SportTournamentSupportSystem.service.impl.JwtService;
import doan2020.SportTournamentSupportSystem.service.impl.UserService;

@RestController
@CrossOrigin
@RequestMapping("/tournament")
public class TournamentAPI {
	@Autowired
	private ITournamentService service;

	@Autowired
	private UserService userService;

	@Autowired
	private SportConverter sportConverter;

	@Autowired
	private ISportService sportService;

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private TournamentConverter converter;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private IPermissionService permissionService;

	@Autowired
	private PermissionConverter permissionConverter;

	@Autowired
	private JwtService jwtService;

	@GetMapping("")
	public ResponseEntity<Response> getTournament(
			@RequestHeader(value = Const.TOKEN_HEADER, required = false) String jwt,
			@RequestParam(value = "id", required = false) Long id) {
		System.out.println("TournamentAPI: getTournament: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		TournamentEntity tournamentEntity = new TournamentEntity();
		TournamentDTO tournamentDTO = new TournamentDTO();
		UserEntity user = new UserEntity();
		PermissionEntity permissionEntity = new PermissionEntity();
		PermissionDTO permissionDTO = new PermissionDTO();
		Map<String, Object> otherInformation = new HashMap<String, Object>();

		try {
			if (id == null) { // id null
				result.put("Tournament", tournamentDTO);
				result.put("OtherInformation", otherInformation);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null

				tournamentEntity = service.findOneById(id);

				if (tournamentEntity == null) { // not found
					result.put("Tournament", tournamentDTO);
					result.put("OtherInformation", otherInformation);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found

					tournamentDTO = converter.toDTO(tournamentEntity);

					otherInformation = service.getOtherInformation(id);

					Long curentUserId = -1l;

					try {
						String curentUserName = jwtService.getUserNameFromJwtToken(jwt);
						user = userService.findByUsername(curentUserName);
						curentUserId = user.getId();
					} catch (Exception e) {

					}

					if (curentUserId == tournamentEntity.getCreator().getId()) {
						permissionEntity = permissionService.findOneByName(Const.OWNER);

						permissionDTO = permissionConverter.toDTO(permissionEntity);
					} else {
						permissionEntity = permissionService.findOneByName(Const.VIEWER);

						permissionDTO = permissionConverter.toDTO(permissionEntity);
					}

					result.put("Tournament", tournamentDTO);
					result.put("OtherInformation", otherInformation);
					config.put("Global", permissionDTO);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("TournamentAPI: getTournament: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: getTournament: has exception");
			result.put("Tournament", null);
			result.put("OtherInformation", otherInformation);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TournamentAPI: getTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Tournament
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createTournament(@RequestBody TournamentDTO newTournament) {
		System.out.println("TournamentAPI: createTournament: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TournamentEntity tournamentEntity = new TournamentEntity();

		try {
			tournamentEntity = converter.toEntity(newTournament);

			tournamentEntity = service.create(tournamentEntity);

			TournamentDTO dto = converter.toDTO(tournamentEntity);

			result.put("Tournament", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Tournament create successfuly");
			System.out.println("TournamentAPI: createTournament: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: createTournament: has exception");
			result.put("Tournament", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TournamentAPI: createTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot Tournament
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editTournament(@RequestBody TournamentDTO tournament, @RequestParam Long id) {
		System.out.println("TournamentAPI: editTournament: start");

		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TournamentEntity tournamentEntity = new TournamentEntity();

		try {
			tournamentEntity = converter.toEntity(tournament);

			tournamentEntity = service.update(id, tournamentEntity);

			TournamentDTO dto = converter.toDTO(tournamentEntity);

			result.put("Tournament", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Tournament update successfuly");
			System.out.println("TournamentAPI: editTournament: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: editTournament: has exception");
			result.put("Tournament", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TournamentAPI: editTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@PostMapping("/uploadAvatar")
	public ResponseEntity<Response> uploadAvatar(@RequestBody MultipartFile file, @RequestParam Long id) {

		System.out.println("TournamentAPI: uploadAvatar: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		System.out.println("TournamentAPI: uploadAvatar: CP1");
		System.out.println(id);
		System.out.println(file.getOriginalFilename());
		try {
			if (id == null) {// id null
				result.put("Tournament", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else {// id not null
				System.out.println("TournamentAPI: uploadAvatar: CP2");
				System.out.println(file);
				String name = service.findOneById(id).getShortName();
				String fileName = fileStorageService.storeFileImage(file, name, Const.AVATAR);
				System.out.println("TournamentAPI: uploadAvatar: CP3");
				if (fileName == null) {// fileName invalid
					result.put("Tournament", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Could not store file");
				} else {// fileName valid
					System.out.println("check point");
					TournamentDTO dto = new TournamentDTO();
					dto.setAvatar(fileName);
					TournamentEntity tournamentEntity = converter.toEntity(dto);
					tournamentEntity = service.updateAvatar(id, tournamentEntity);

					result.put("Tournament", converter.toDTO(tournamentEntity));
					error.put("MessageCode", 0);
					error.put("Message", "Upload avatar and Edit Tournament Successfull");
				}
			}
			System.out.println("TournamentAPI: uploadAvatar: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: uploadAvatar: has exception");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("TournamentAPI: uploadAvatar: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@PostMapping("/uploadBackground")
	public ResponseEntity<Response> uploadBackground(@RequestBody MultipartFile file, @RequestParam Long id) {

		System.out.println("TournamentAPI: uploadBackground: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		System.out.println("TournamentAPI: uploadAvatar: CP1");
		System.out.println(id);
		System.out.println(file.getOriginalFilename());
		try {
			if (id == null) {// id null
				result.put("Tournament", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else {// id not null
				System.out.println("TournamentAPI: uploadAvatar: CP2");
				System.out.println(file);
				String name = service.findOneById(id).getShortName();
				String fileName = fileStorageService.storeFileImage(file, name, Const.BACKGROUND);
				System.out.println("TournamentAPI: uploadAvatar: CP3");
				if (fileName == null) {// fileName invalid
					result.put("Tournament", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Could not store file");
				} else {// fileName valid
					System.out.println("check point");
					TournamentDTO dto = new TournamentDTO();
					dto.setBackground(fileName);
					TournamentEntity tournamentEntity = converter.toEntity(dto);
					tournamentEntity = service.updateBackground(id, tournamentEntity);

					result.put("Tournament", converter.toDTO(tournamentEntity));
					error.put("MessageCode", 0);
					error.put("Message", "Upload background and Edit Tournament Successfull");
				}
			}
			System.out.println("TournamentAPI: uploadBackground: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: uploadBackground: has exception");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("TournamentAPI: uploadBackground: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	
	@PostMapping("/start")
	public ResponseEntity<Response> startTournament(@RequestParam Long id) {
		System.out.println("TournamentAPI: startTournament: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		
		TournamentEntity thisTournament = new TournamentEntity();
		TournamentDTO thisTournamentDTO = new TournamentDTO();
		
		try {
			if (id == null) {// id null
				result.put("Tournament", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else {// id not null
				
				thisTournament = service.findOneById(id);
				if (thisTournament == null) {
					result.put("Tournament", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Tournament is not exist");
				} else {
					Collection<CompetitionEntity> comps = thisTournament.getCompetitions();
					ArrayList<HashMap<String, Object>> tests = new ArrayList<>();
					for (CompetitionEntity comp: comps) {
						migratePlayersFromFileSystemToDatabase(comp);
						HashMap<String, Object> test = migrateMatchesFromFileSystemToDatabase(comp);
						test.put("CompetitionId", comp.getId());
						test.put("CompetitionName", comp.getName());
						tests.add(test);
					}
					
					result.put("Schedule", tests);
					
					result.put("Tournament", thisTournamentDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Success");
				}
				
			}
			System.out.println("TournamentAPI: startTournament: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: startTournament: has exception");
			result.put("Tournament", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("TournamentAPI: startTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	private void migratePlayersFromFileSystemToDatabase(CompetitionEntity competitionEntity) {
		// TODO Auto-generated method stub
		
	}

	private HashMap<String, Object> migrateMatchesFromFileSystemToDatabase(CompetitionEntity competitionEntity) {
		Collection<MatchEntity> matches = new ArrayList<>();
		String fileName = "comp_" + competitionEntity.getId() + ".conf";
		
		String absFolderPath;
		try {
			absFolderPath = fileStorageService.getFileStorageLocation(Const.BRANCH_CONFIG_FOLDER).toString();
			HashMap<String, Object> schedule = (HashMap<String, Object>) fileStorageService.getObjectFromFile(absFolderPath + "\\" + fileName);
			
			return schedule;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
