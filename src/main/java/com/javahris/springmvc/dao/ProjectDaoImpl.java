package com.javahris.springmvc.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.javahris.springmvc.model.Project;

@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Integer, Project> implements ProjectDao{

	public Project findById(int id) {
		return getByKey(id);
	}
	
	public Project findByTeamLeadId(int id){
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("projTeamLead", id));
        return (Project) criteria.uniqueResult();
	}

}
