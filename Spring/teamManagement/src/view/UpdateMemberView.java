package view;

import dao.MemberDAO;
import dao.TeamDAO;
import dto.MemberDTO;
import dto.TeamDTO;

public class UpdateMemberView extends MainView {
	private static final long serialVersionUID = 1L;
	
	private MemberDAO memberDAO;
	private TeamDAO teamDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	@Override
	public void display(MainView mainView) {
		final String field = "팀원 ID";
		String id = getKeyInputString(field);
		
		if(!Utils.isEmpty(id, field) && Utils.isSmallLength(id, field, 3) && Utils.isNumeric(id, field)) {
			MemberDTO member = memberDAO.getMember(Integer.valueOf(id));
			if(member == null) {
				msg(":::팀원 ID[" + id + "]는 존재하지 않는 번호입니다");
			} else {
				setTeam(member);
			}
		}
	}
	
	public void setTeam(MemberDTO member) {
		final String field = "팀 ID";
		String id = getKeyInputString(field);
		if(!Utils.isEmpty(id, field) && Utils.isSmallLength(id, field, 3) && Utils.isNumeric(id, field)) {
			TeamDTO team = teamDAO.getTeam(Integer.valueOf(id));
			if(team == null) {
				msg(":::팀 ID[" + id + "]는 존재하지 않는 번호입니다");
			} else {
				member.setTeam(team);
				memberDAO.updateMember(member);
				msg(":::팀원 ID[" + member.getId() + "]를 팀ID[" + id + "]로 수정하였습니다");
			}
			
		}
	}

}
