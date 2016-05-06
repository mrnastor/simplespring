package com.javahris.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="LEAVE_TYPES")
public class Leave_Types {

    @Id
    @Column(name = "LEAVE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;
    
    @NotNull
    @Size(min=1, max=45)
    @Column(name = "LEAVE_NAME", nullable = false)
    private String leaveName;

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + leaveId;
		result = prime * result + ((leaveName == null) ? 0 : leaveName.hashCode());
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
		Leave_Types other = (Leave_Types) obj;
		if (leaveId != other.leaveId)
			return false;
		if (leaveName == null) {
			if (other.leaveName != null)
				return false;
		} else if (!leaveName.equals(other.leaveName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Leave_Types [leaveId=" + leaveId + ", leaveName=" + leaveName + "]";
	}
    
    
}
