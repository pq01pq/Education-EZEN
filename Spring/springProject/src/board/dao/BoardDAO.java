package board.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import board.dto.BoardDTO;

public interface BoardDAO {
	public int insert(BoardDTO article) throws DataAccessException;
	public List<BoardDTO> selectAll() throws DataAccessException;
	public List<BoardDTO> select(String column, String key) throws DataAccessException;
	public List<BoardDTO> select(int num) throws DataAccessException;
	public List<BoardDTO> selectPage(int pageNum, int numPerPage) throws DataAccessException;
	public List<BoardDTO> searchAll() throws DataAccessException;
	public List<BoardDTO> search(String key) throws DataAccessException;
	public List<BoardDTO> search(String column, String key) throws DataAccessException;
	public int update(BoardDTO article) throws DataAccessException;
	public int delete(int num) throws DataAccessException;
	public int count() throws DataAccessException;
	public int maxGroup() throws DataAccessException;
}
