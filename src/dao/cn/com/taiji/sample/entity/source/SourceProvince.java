package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 省份
 */
@Entity
@Table(name = "source_province")
public class SourceProvince extends StringUUIDEntity
{
	private String provinceId;
	private String provinceName;
	@Column(name="province_Id",nullable = false, length = 100)
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name="province_Name",nullable = false, length = 100)
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
}
