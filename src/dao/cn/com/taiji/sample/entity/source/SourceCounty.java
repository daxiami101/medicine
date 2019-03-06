package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 区
 */
@Entity
@Table(name = "source_county")
public class SourceCounty extends StringUUIDEntity
{
	private String cityId;
	private String countyId;
	private String countyName;
	@Column(name="city_Id",nullable = false, length = 100)
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Column(name="county_Id",nullable = false, length = 100)
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	@Column(name="county_Name",nullable = false, length = 100)
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	
}
