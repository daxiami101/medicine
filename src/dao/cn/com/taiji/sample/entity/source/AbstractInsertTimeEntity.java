package cn.com.taiji.sample.entity.source;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import cn.com.taiji.common.entity.StringUUIDEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:38:56
 * TODO
 */
@MappedSuperclass
public abstract class AbstractInsertTimeEntity extends StringUUIDEntity
{
	protected LocalDateTime insertTime = LocalDateTime.now();
	protected String dataSource;
	@Column(name = "INSERT_TIME", nullable = false,updatable=false)
	public LocalDateTime getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}
	@Column(name = "DATA_SOURCE", nullable = false)
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}




	
}