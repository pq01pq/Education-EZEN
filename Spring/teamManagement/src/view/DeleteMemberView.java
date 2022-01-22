package view;

import dao.MemberDAO;
import dto.MemberDTO;

public class DeleteMemberView extends MainView {
	private static final long serialVersionUID = 1L;
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void display(MainView mainView) {
		final String memberId = "팀원 ID";
		String id = getKeyInputString(memberId);
		
		if(Utils.isEmpty(id, memberId)) {
			return;
		} else if(Utils.isSmallLength(id, memberId, 80)) {
			MemberDTO member = memberDAO.getMember(Integer.valueOf(id));
			if(member == null) {
				msg(":::팀원 ID[" + id + "]는 존재하지 않는 번호입니다");
				return;
			} else {
				memberDAO.deleteMember(member);
				msg(":::팀원 ID[" + id + "]를 삭제하였습니다");
			}
		}
	}

}
