package com.javahris.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="PROJECT")
public class Project {

    @Id
    @Column(name = "PROJECT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projId;
    
    @Size(min=0, max=256)
    @Column(name = "PROJECT_NAME", nullable = true)
    private String projName;
    
    @NotNull
    @Digits(integer=11, fraction=0)
    @Column(name = "PROJECT_TEAM_LEAD", nullable = false)
    private int projTeamLead;

	public int getProjId() {
		return projId;
	}

	public void setProjId(int projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public int getProjTeamLead() {
		return projTeamLead;
	}

	public void setProjTeamLead(int projTeamLead) {
		this.projTeamLead = projTeamLead;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + projId;
		result = prime * result + ((projName == null) ? 0 : projName.hashCode());
		result = prime * result + projTeamLead;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (projId != other.projId)
			return false;
		if (projName == null) {
			if (other.projName != null)
				return false;
		} else if (!projName.equals(other.projName))
			return false;
		if (projTeamLead != other.projTeamLead)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [projId=" + projId + ", projName=" + projName + ", projTeamLead=" + projTeamLead + "]";
	}
    
    
}
