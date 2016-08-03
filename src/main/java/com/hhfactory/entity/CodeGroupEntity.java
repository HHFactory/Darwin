package com.hhfactory.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "code_groups")
public class CodeGroupEntity extends AbstractEntityIdOnly{
	/** コードグループ名 */
	@Column(nullable = false)
	private String name;
	
	/** コードグループ英語名 */
	@Column(nullable = false)
	private String alias;
	
	/** コード値リスト */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "code_group_id", referencedColumnName = "id")
	private List<CodeValueEntity> codeValues;
}
