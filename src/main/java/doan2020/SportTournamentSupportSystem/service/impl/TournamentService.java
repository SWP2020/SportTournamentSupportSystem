
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.repository.TournamentRepository;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;

@Service
public class TournamentService implements ITournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private CompetitionService competitionService;

	@Override
	public TournamentEntity create(TournamentEntity tournamentEntity) {
		TournamentEntity newEntity = null;
		try {
			newEntity = tournamentRepository.save(tournamentEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public TournamentEntity update(Long id, TournamentEntity newEntity) {
		TournamentEntity updatedEntity = null;
		try {
			updatedEntity = tournamentRepository.findOneById(id);

			updatedEntity.setFullName(newEntity.getFullName());
			updatedEntity.setShortName(newEntity.getShortName());
			updatedEntity.setDescription(newEntity.getDescription());
//			updatedEntity.setCreator(newEntity.getCreator());
			updatedEntity.setOpeningLocation(newEntity.getOpeningLocation());
			updatedEntity.setOpeningTime(newEntity.getOpeningTime());
			updatedEntity.setClosingLocation(newEntity.getClosingLocation());
			updatedEntity.setClosingTime(newEntity.getClosingTime());
			updatedEntity.setDonor(newEntity.getDonor());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = tournamentRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}

		return updatedEntity;
	}

	@Override
	public TournamentEntity delete(Long id) {
		TournamentEntity deletedEntity = null;
		try {
			deletedEntity = tournamentRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = tournamentRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public TournamentEntity findOneById(Long id) {
		TournamentEntity foundEntity = null;
		try {
			foundEntity = tournamentRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<TournamentEntity> findByCreatorId(Pageable pageable, Long creatorId) {
		Collection<TournamentEntity> findTournaments = null;
		try {
			findTournaments = tournamentRepository.findByCreatorId(pageable, creatorId).getContent();
		} catch (Exception e) {
			return null;
		}
		return findTournaments;
	}

	@Override
	public Collection<TournamentEntity> findAll(Pageable pageable) {
		Collection<TournamentEntity> findTournaments = null;
		try {
			findTournaments = tournamentRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return findTournaments;
	}

	@Override
	public Collection<TournamentEntity> findBySearchString(Pageable pageable, String searchString) {
		List<TournamentEntity> findTournaments = null;
		try {
			
			findTournaments = (List) tournamentRepository.findBySearchString(searchString);
			
			int start = (int) pageable.getOffset();
			int end = (start + pageable.getPageSize()) > findTournaments.size() ? findTournaments.size() : (start + pageable.getPageSize());
			Page<TournamentEntity> pages = new PageImpl<TournamentEntity>(findTournaments.subList(start, end), pageable, findTournaments.size());
			findTournaments = pages.getContent();
			
		} catch (Exception e) {
			return null;
		}
		return findTournaments;
	}

	@Override
	public int countBySearchString(String searchString) {
		List<TournamentEntity> findTournaments = null;
		int count = 0;
		try {
			findTournaments = (List) tournamentRepository.findBySearchString(searchString);
			
			count = findTournaments.size();
		} catch (Exception e) {
			return 0;
		}
		return count;
	}

	@Override
	public TournamentEntity updateAvatar(Long id, TournamentEntity newEntity) {
		TournamentEntity updatedEntity = null;
		try {
			updatedEntity = tournamentRepository.findOneById(id);

//			updatedEntity.setAvartar(newEntity.getAvatar);
			updatedEntity = tournamentRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}

		return updatedEntity;
	}

	@Override
	public TournamentEntity updateBackground(Long id, TournamentEntity newEntity) {
		TournamentEntity updatedEntity = null;
		try {
			updatedEntity = tournamentRepository.findOneById(id);

//			updatedEntity.setBackground(newEntity.getBackground();
			updatedEntity = tournamentRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}

		return updatedEntity;
	}

	@Override
	public Map<String, Object> getOtherInformation(Long id) {
		Map<String, Object> option = new HashMap<String, Object>();
		Collection<CompetitionEntity> competitions = competitionService.findByTournamentId(id);

		HashSet<String> sportsName = new HashSet<>();

		int countPlayer = 0;

		for (CompetitionEntity comp : competitions) {

			sportsName.add(comp.getSport().getFullName());

			for (TeamEntity teamEntity : comp.getTeams()) {
				countPlayer += teamEntity.getPlayers().size();
			}
		}
		option.put("sportsName", sportsName);
		option.put("countPlayer", countPlayer);
		option.put("process", 0);
		return option;
	}

}