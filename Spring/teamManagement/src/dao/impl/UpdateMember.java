package dao.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateMember extends SqlUpdate {
	public UpdateMember(DataSource dataSource) {
		super(dataSource, "update sts_member set team_id=? where member_id=?");
		super.declareParameter(new SqlParameter("team_id", Types.INTEGER));
		super.declareParameter(new SqlParameter("member_id", Types.INTEGER));
		super.compile();
	}
}
