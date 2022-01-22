package dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dto.TeamDTO;

public interface TeamDAO {
	List<TeamDTO> getTeamList() throws DataAccessException;
	TeamDTO getTeam(Integer id) throws DataAccessException;
}
