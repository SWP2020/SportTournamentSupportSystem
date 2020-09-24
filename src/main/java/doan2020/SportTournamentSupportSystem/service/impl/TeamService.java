
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.model.Entity.Player;
import doan2020.SportTournamentSupportSystem.repository.TeamRepository;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private IFileStorageService fileService;

	@Override
	public TeamEntity create(TeamEntity teamEntity) {
		System.out.println("TeamService: create: start");
		TeamEntity newEntity = null;
		try {
			if (teamEntity.getStatus() == Const.TEAM_STATUS_PENDING) {
				System.out.println("TeamService: create: TEAM_STATUS_PENDING");
				newEntity = teamRepository.save(teamEntity);
			} else if (teamEntity.getStatus() == Const.TEAM_STATUS_JOINED) {
				System.out.println("TeamService: create: TEAM_STATUS_JOINED");
				Long TournamentId = teamEntity.getTournament().getId();
				Long maxSeedNo = getMaxSeedNoByTournamentId(TournamentId);
				teamEntity.setSeedNo(maxSeedNo + 1l);
				newEntity = teamRepository.save(teamEntity);
				System.out.println("TeamService: create: no exception");
			}
		} catch (Exception e) {
			System.out.println("TeamService: create: has exception");
			System.out.println(e);
			
		}
		System.out.println("TeamService: create: finish");
		return newEntity;
	}

	@Override
	public TeamEntity update(Long id, TeamEntity newEntity) {
		TeamEntity updatedEntity = null;
		try {
			updatedEntity = teamRepository.findOneById(id);

			updatedEntity.setFullName(newEntity.getFullName());
			updatedEntity.setShortName(newEntity.getShortName());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setCreator(newEntity.getCreator());
			updatedEntity.setTournament(newEntity.getTournament());
			if (newEntity.getStatus() != null) {updatedEntity.setStatus(newEntity.getStatus());}
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = teamRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}

		return updatedEntity;
	}

	@Override
	public TeamEntity delete(Long id) {
		TeamEntity deletedEntity = null;
		try {
			deletedEntity = teamRepository.findOneById(id);
			teamRepository.delete(deletedEntity);
//			deletedEntity.setStatus("deleted");
//			deletedEntity = teamRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public TeamEntity findOneById(Long id) {
		TeamEntity foundEntity = null;
		try {
			foundEntity = teamRepository.getOne(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<TeamEntity> findAll(Pageable pageable) {
		Collection<TeamEntity> foundEntitys = null;
		try {
			foundEntitys = teamRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<TeamEntity> findAll() {
		Collection<TeamEntity> foundEntitys = null;
		try {
			foundEntitys = teamRepository.findAll();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<TeamEntity> findByCreatorId(Long creatorId) {
		Collection<TeamEntity> foundEntitys = null;
		try {
			foundEntitys = teamRepository.findByCreatorId(creatorId);
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<TeamEntity> findByTournamentId(Long TournamentId) {
		Collection<TeamEntity> foundEntities = null;
		try {
			foundEntities = teamRepository.findByTournamentId(TournamentId);
		} catch (Exception e) {
			return null;
		}
		return foundEntities;
	}

	@Override
	public Collection<TeamEntity> swap(Long team1Id, Long team2Id) {
		Collection<TeamEntity> teams = null;
		try {
			TeamEntity team1 = teamRepository.findOneById(team1Id);
			TeamEntity team2 = teamRepository.findOneById(team2Id);
			TournamentEntity thisComp = team1.getTournament();
			Long compId = thisComp.getId();
			
			Long tmp = team1.getSeedNo();
			team1.setSeedNo(team2.getSeedNo());
			team2.setSeedNo(tmp);
			teamRepository.save(team1);
			teamRepository.save(team2);
			
			teams = teamRepository.findByTournamentId(compId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return teams;
	}
	
	public String saveTeamPlayersToFile(TeamEntity team, Collection<Player> players) {

		String absFolderPath = null;
		String absFilePath = null;

		try {
			TournamentEntity comp = team.getTournament();

			String folder = Const.TOURNAMENT_FILESYSTEM + Const.TOURNAMENT_FOLDER_NAMING + comp.getId();
			absFolderPath = fileService.getFileStorageLocation(folder).toString();
			String fileName = Const.TOURNAMENT_TEAM_PLAYERS_NAMING + team.getId()
					+ Const.FILE_EXTENDED;
			absFilePath = absFolderPath + "\\" + fileName;
			absFilePath = fileService.saveObjectToFile(players, absFilePath);

			System.out.println("TeamAPI: saveTeamConfig: absFileName: \n[" + absFilePath + "]");
		} catch (Exception e) {
		}

		return absFilePath;
	}

	@SuppressWarnings("unchecked")
	public Collection<Player> getTeamPlayerFromFile(Long TournamentId, Long teamId) {

		String absFolderPath = null;
		String absFilePath = null;
		ArrayList<Player> players = new ArrayList<>();

		try {

			String folder = Const.TOURNAMENT_FILESYSTEM + Const.TOURNAMENT_FOLDER_NAMING + TournamentId;
			absFolderPath = fileService.getFileStorageLocation(folder).toString();
			String fileName = Const.TOURNAMENT_TEAM_PLAYERS_NAMING + teamId
					+ Const.FILE_EXTENDED;
			absFilePath = absFolderPath + "\\" + fileName;
			players = (ArrayList<Player>) fileService.getObjectFromFile(absFilePath);

		} catch (Exception e) {
		}

		return players;
	}
	
	@Override
	public Collection<TeamEntity> findByTournamentIdAndStatus(Long TournamentId, String status) {
		Collection<TeamEntity> foundEntities = null;
		try {
			foundEntities = teamRepository.findByTournamentIdAndStatus(TournamentId, status);
		} catch (Exception e) {
			return null;
		}
		return foundEntities;
	}
	
	@Override
	public Long getMaxSeedNoByTournamentId(Long TournamentId) {
		Long maxSeedNo = 0l;
		try {
			maxSeedNo = teamRepository.getMaxSeedNoByTournamentId(TournamentId);
			if (maxSeedNo == null) {
				return 0l;
			}
		} catch (Exception e) {
		}
		
		return maxSeedNo;
	}
	
	@Override
	public Long countByTournamentIdAndStatus(Long TournamentId, String status) {
		Long totalTeam = 0l;
		try {
			totalTeam = teamRepository.countByTournamentIdAndStatus(TournamentId, status);
			if (totalTeam == null) {
				return 0l;
			}
		} catch (Exception e) {
		}
		
		return totalTeam;
	}

}