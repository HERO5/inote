package com.mrl.inote.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

/**
 * 定义实体的基础公共属性，所有实体都会继承.
 * @author Jeff Xu
 * @since 2015-12-09
 */
@MappedSuperclass
public class BaseEntity extends MysqlSequenceIdEntity implements Serializable{

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

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) object;
			if (this.getId() == null || baseEntity.getId() == null) {
				return false;
			} else {
				return (this.getId().equals(baseEntity.getId()));
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
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
