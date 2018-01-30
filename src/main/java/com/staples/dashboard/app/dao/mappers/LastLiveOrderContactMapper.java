
package com.staples.dashboard.app.dao.mappers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.sfdc.Task;

public class LastLiveOrderContactMapper implements RowMapper<Task> , StaplesDashBoardRowMapper  {

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<Object> objects = null;
		String firstName=" ";
		String lastName=" ";
	    Task task = new Task();
		
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        
	        	firstName=rs.get(MapperConstants.LAST_LIVE_ORDER_CONTACT_FIRSTNAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.LAST_LIVE_ORDER_CONTACT_FIRSTNAME).toString();
	            lastName=rs.get(MapperConstants.LAST_LIVE_ORDER_CONTACT_LASTNAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.LAST_LIVE_ORDER_CONTACT_LASTNAME).toString();
	        
	      	    task.setWhoId(firstName+" "+lastName);
	  	        objects.add(task);
	        
	       
	      }}
	   
		
		return objects;
	}

	
	@Override
	public Task mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Task task=new Task ();
	    task.setWhoId(rs.getString(MapperConstants.LAST_LIVE_ORDER_CONTACT_FIRSTNAME)+rs.getString(MapperConstants.LAST_LIVE_ORDER_CONTACT_LASTNAME));
	
	  return task;
	}

}

