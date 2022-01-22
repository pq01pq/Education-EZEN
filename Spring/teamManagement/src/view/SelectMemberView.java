package view;

import java.util.List;
import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import dao.MemberDAO;
import dto.MemberDTO;

public class SelectMemberView extends MainView {
	private static final long serialVersionUID = 1L;

	private MainView mainView;
	
	private MemberDAO memberDAO;
	private DeleteMemberView deleteMemberView;
	private UpdateMemberView updateMemberView;
	
	private JTextArea viewArea;
	private JButton menuButton, modifyButton, deleteButton;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	public void setDeleteMemberView(DeleteMemberView deleteMemberView) {
		this.deleteMemberView = deleteMemberView;
	}
	public void setUpdateMemberView(UpdateMemberView updateMemberView) {
		this.updateMemberView = updateMemberView;
	}
	
	protected Integer getTeamId() {
		while(true) {
			String field = "팀 ID";
			String id = JOptionPane.showInputDialog(field + "입력");
			if(!Utils.isEmpty(id, field) && Utils.isNumeric(id, field) && Utils.isSmallLength(id, field, 2)) {
				return Integer.valueOf(id);
			}
		}
	}
	
	public void display(MainView mainView) {
		this.mainView = mainView;
		String field = "팀 ID";
		String teamId = getKeyInputString(field);
		if(Utils.isEmpty(teamId, field) || !Utils.isNumeric(teamId, field) || !Utils.isSmallLength(teamId, field, 2)) {
			return;
		}
		List<MemberDTO> listMember = memberDAO.getMembers(Integer.valueOf(teamId));
		showMemberList(listMember);
	}
	
	protected void showMemberList(List<MemberDTO> listMember) {
		mainView.viewPanel.removeAll();
		mainView.buttonPanel.removeAll();
		menuButton = new JButton("메뉴");
		mainView.buttonPanel.add(menuButton);
		menuButton.setActionCommand("menu");
		menuButton.addMouseListener(this);
		viewArea = new JTextArea();
		mainView.viewPanel.add(viewArea);
		
		if(listMember == null || listMember.size() == 0) {
			viewArea.setText("선수가 없습니다.");
			return;
		}
		
		viewArea.setText("----- 선수 목록 -----\n");
		viewArea.append("ID\t\t이름\n");
		for(MemberDTO member : listMember) {
			viewArea.append(member.getId() + "\t\t" + member.getName() + "\n");
		}
		modifyButton = new JButton("팀원 수정");
		deleteButton = new JButton("팀원 삭제");
		mainView.buttonPanel.add(modifyButton);
		mainView.buttonPanel.add(deleteButton);
		modifyButton.setActionCommand("mod");
		deleteButton.setActionCommand("del");
		modifyButton.addMouseListener(this);
		deleteButton.addMouseListener(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton)e.getSource();
			switch(button.getActionCommand()) {
			case "menu":{
				mainView.display();
				break;
			}
			case "mod":{
				updateMemberView.display(mainView);
				break;
			}
			case "del":{
				deleteMemberView.display(mainView);
				break;
			}
			}
		}
	}

}
