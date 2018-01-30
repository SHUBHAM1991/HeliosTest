package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.dto.AccountUserDTO;

public class AccountUserMapper implements RowMapper<AccountUserDTO>,
		StaplesDashBoardRowMapper {

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<Object> objects = null;
		AccountUserDTO account = null;
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				if (objects == null) {
					objects = new ArrayList<Object>();
				}
				account = new AccountUserDTO();
				account.setNumber(rs.get(MapperConstants.EMP_NUMBER) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.EMP_NUMBER).toString());
				account.setFullname(rs.get(MapperConstants.EMP_FULL_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.EMP_FULL_NAME).toString());
				account.setDesignation(rs.get(MapperConstants.EMP_DESIGNATION) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.EMP_DESIGNATION).toString());
				account.setRepRoleCd(rs.get(MapperConstants.EMP_REP_ROLE_CD) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.EMP_REP_ROLE_CD).toString());
				account.setLevel(rs.get(MapperConstants.EMP_LEVEL) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.EMP_LEVEL).toString());
				account.setCustomerNo(rs.get(MapperConstants.CUSTOMER_NUMBER) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_NUMBER).toString());
				account.setCompanyName(rs.get(MapperConstants.COMPANY_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.COMPANY_NAME).toString());
				account.setAssignGrp(rs.get("ASSIGN_ACCOUNT_TO") == null ? MapperConstants.BLANK_STRING
						: rs.get("ASSIGN_ACCOUNT_TO").toString());
				objects.add(account);
			}
		}
		return objects;
	}

	@Override
	public AccountUserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		AccountUserDTO account = new AccountUserDTO();

		account.setNumber(rs.getString(MapperConstants.EMP_NUMBER));
		account.setFullname(rs.getString(MapperConstants.EMP_FULL_NAME));
		account.setDesignation(rs.getString(MapperConstants.EMP_DESIGNATION));
		account.setRepRoleCd(rs.getString(MapperConstants.EMP_REP_ROLE_CD));
		account.setLevel(rs.getString(MapperConstants.EMP_LEVEL));
		account.setCustomerNo(rs.getString(MapperConstants.CUSTOMER_NUMBER));
		account.setCompanyName(rs.getString(MapperConstants.COMPANY_NAME));
		return account;
	}

	public Object mapRow(Map<String, Object> rs) {

		final AccountUserDTO account = new AccountUserDTO();
		account.setNumber(rs.get(MapperConstants.EMP_NUMBER) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.EMP_NUMBER).toString());
		account.setFullname(rs.get(MapperConstants.EMP_FULL_NAME) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.EMP_FULL_NAME).toString());
		account.setDesignation(rs.get(MapperConstants.EMP_DESIGNATION) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.EMP_DESIGNATION).toString());
		account.setRepRoleCd(rs.get(MapperConstants.EMP_REP_ROLE_CD) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.EMP_REP_ROLE_CD).toString());
		account.setLevel(rs.get(MapperConstants.EMP_LEVEL) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.EMP_LEVEL).toString());
		account.setCustomerNo(rs.get(MapperConstants.CUSTOMER_NUMBER) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.CUSTOMER_NUMBER).toString());
		account.setCompanyName(rs.get(MapperConstants.COMPANY_NAME) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.COMPANY_NAME).toString());
		return account;
	}
}
