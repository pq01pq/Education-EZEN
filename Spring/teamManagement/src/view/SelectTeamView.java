package view;

import java.util.List;
import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import dao.TeamDAO;
import dto.TeamDTO;

public class SelectTeamView extends MainView {
	private static final long serialVersionUID = 1L;
	
	private MainView mainView;
	private JTextArea viewArea;
	
	private TeamDAO teamDAO;
	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}
	
	@Override
	public void display(MainView mainView) {
		this.mainView = mainView;
		List<TeamDTO> teams = teamDAO.getTeamList();
		mainView.viewPanel.removeAll();
		viewArea = new JTextArea();
		mainView.viewPanel.add(viewArea);
		showTeamList(teams);
	}
	
	public void showTeamList(List<TeamDTO> teams) {
		viewArea.setText("----- 팀 목록 -----\n");
		viewArea.append("팀 ID\t\t팀명\n");
		for(TeamDTO team : teams) {
			viewArea.append(team.getId() + "\t\t" + team.getName() + "\n");
		}
	}

}
