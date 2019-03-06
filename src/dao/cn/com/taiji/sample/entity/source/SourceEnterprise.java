package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 经营者信息
 */
@Entity
@Table(name = "source_enterprise")
public class SourceEnterprise extends AbstractInsertTimeEntity
{
	private String name;//企业名称
	private String code ;//许可证号
	private String provinceId;//省
	private String cityId;//市、县
	private String countyId;//区
	private Integer employeeNum;//企业员工数量
	@Column(name="name",nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="code",nullable = false, length = 100)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	@Column(name="employee_Num",nullable = false, length = 100)
	public Integer getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}
}
