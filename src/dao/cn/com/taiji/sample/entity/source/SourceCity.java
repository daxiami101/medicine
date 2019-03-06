package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 市县
 */
@Entity
@Table(name = "source_city")
public class SourceCity extends StringUUIDEntity
{
	private String provinceId;
	private String cityId;
	private String cityName;
	@Column(name="province_Id",nullable = false, length = 100)
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name="city_Id",nullable = false, length = 100)
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Column(name="city_Name",nullable = false, length = 100)
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
}
