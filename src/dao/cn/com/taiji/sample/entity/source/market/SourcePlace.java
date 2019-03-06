package cn.com.taiji.sample.entity.source.market;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 产地管理
 */
@Entity
@Table(name = "source_place")
public class SourcePlace extends AbstractInsertTimeEntity
{
	private String medicineName;
	private String provinceId;//省编号
	private String cityId;//市
	private String countyId;//县
	private String address;//详细地址
	@Column(name="medicine_Name",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
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
	@Column(name="county_Id",nullable = false, length = 100)
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	@Column(name="address",nullable = false, length = 100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
