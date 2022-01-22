package view;

import dao.MemberDAO;
import dao.TeamDAO;
import dto.MemberDTO;
import dto.TeamDTO;

public class InsertMemberView extends MainView {
	private static final long serialVersionUID = 1L;
	
	private TeamDAO teamDAO;
	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	@Override
	public void display(MainView mainView) {
		String field = "선수 이름";
		String name = getKeyInputString(field);
		if(Utils.isEmpty(name, field) || !Utils.isSmallLength(name, field, 80)) {
			return;
		}
		MemberDTO member = new MemberDTO();
		member.setName(name);
		showTeamField(member);
	}
	
	public void showTeamField(MemberDTO member) {
		final String field = "팀ID";
		String id = getKeyInputString(field);
		
		if(Utils.isEmpty(id, field) || !Utils.isNumeric(id, field)) {
			return;
		}
		TeamDTO team = teamDAO.getTeam(Integer.valueOf(id));
		while(true) {
			if(team == null) {
				msg(":::::[" + id + "]의 팀은 존재하지 않습니다");
			} else {
				member.setTeam(team);
				memberDAO.insertMember(member);
				msg(":::::[" + team.getName() + "]에 선수 [" + member.getName() + "]를 추가");
				break;
			}
		}
		
	}

}
