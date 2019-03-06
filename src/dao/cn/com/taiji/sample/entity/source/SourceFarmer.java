package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.FarmerType;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 农户档案
 */
@Entity
@Table(name = "source_farmer")
public class SourceFarmer extends AbstractInsertTimeEntity
{
	private String farmerNo;//农户代码
	private FarmerType farmerType;//农户类型
	private String location;//省
	private String medicineName;//	种植药材名称
	private String contractNum;//种植合同号
	private Double area;//面积（亩）
	private String aduitStatus;//	审核状态
	private String dataSource;//数据源
	@Column(name="data_Source",nullable = false, length = 100)
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	@Column(name="FARMER_NO",nullable = false, length = 100)
	public String getFarmerNo() {
		return farmerNo;
	}
	public void setFarmerNo(String farmerNo) {
		this.farmerNo = farmerNo;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="FARMER_TYPE",nullable = false, length = 100)
	public FarmerType getFarmerType() {
		return farmerType;
	}
	
	public void setFarmerType(FarmerType farmerType) {
		this.farmerType = farmerType;
	}
	@Column(name="MEDICINE_NAME",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	@Column(name="CONTRACT_NUM",nullable = false, length = 100)
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	@Column(name="AREA",nullable = false, length = 100)
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	@Column(name="AUDIT_STATUS",nullable = false, length = 100)
	public String getAduitStatus() {
		return aduitStatus;
	}
	public void setAduitStatus(String aduitStatus) {
		this.aduitStatus = aduitStatus;
	}
	@Column(name="location",nullable = false, length = 100)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
