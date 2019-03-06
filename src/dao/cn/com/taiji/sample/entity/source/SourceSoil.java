package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 土壤类型
 */	
@Entity
@Table(name = "source_soil")
public class SourceSoil extends AbstractInsertTimeEntity
{
	private String name;
	@Column(name="name",nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}//类型名称
	
	
}
