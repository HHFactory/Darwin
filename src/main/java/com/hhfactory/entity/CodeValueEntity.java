package com.hhfactory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * コードグループEntity
 * FIXME:Entityとして定義するか要検討
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "code_values")
public class CodeValueEntity extends AbstractEntityIdOnly{
	/** コード値 */
	@Column(nullable = false)
	private Integer code;
	
	/** コード名 */
	@Column(nullable = false)	
	private String name;
	
	/** コード英語名 */
	@Column(nullable = false)
	private String alias;
}
