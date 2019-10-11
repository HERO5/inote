package com.mrl.inote.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntity_NID implements Serializable{

	private static final long serialVersionUID = -4498233384948128317L;

	/**
	 * 创建日期
	 */
	protected Date createDate;
	/**
	 * 修改日期
	 */
	protected Date updateDate;

	@Column(name = "create_date", updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@PrePersist
    public void prePersist() {
		if(this.createDate == null){
			this.setCreateDate(new Date());
		}
		this.setUpdateDate(new Date());
    }
 
    @PreUpdate
    public void preUpdate() {
    	this.setUpdateDate(new Date());
    }
	
}
