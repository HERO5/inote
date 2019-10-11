package com.mrl.inote.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Mysql数据库的主键生成定义:系统自动生成32位不同的字符序列
 * @author Jeff Xu
 * @since 2015-12-09
 */
@MappedSuperclass
public class MysqlSequenceIdEntity extends IdEntity{
	
	protected String id;

	@Id
	@Column(name = "id")
//	@GenericGenerator(name = "sys_uuid", strategy = "uuid")
//	@GeneratedValue(generator = "sys_uuid")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
