package cn.com.taiji.sample.entity.source.land;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 生长调节剂
 */
@Entity
@Table(name = "source_Growth_Regulator")
public class SourceGrowthRegulator extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String regulatorKind;//生长调节剂种类 
	private Calendar useStartTime;//施用开始时间 	
	private Calendar useEndTime ;//施用结束时间 	
	private String useMethod;//施用方式 	
	private Double num;//施用量/亩
	private String unit;//计量单位
	private String enrollNo;//登记证号
	private String company;//生产厂家
	private Calendar produceDate;//生产日期
	private Calendar purchaseDate;//采购日期
	private String status;
	private String taskNo;//种植批号
	private String medicineName;//药材名称
	private String farmerId;//农户id
	@Column(name="farmer_Id",nullable = false, length = 100)
	public String getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	@Column(name="medicine_Name",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	@Column(name="task_No",nullable = false, length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	@Column(name="task_Id",nullable = false, length = 100)
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name="seed_Id",nullable = false, length = 100)
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	
	
	@Column(name="use_Start_Time",nullable = false, length = 100)
	public Calendar getUseStartTime() {
		return useStartTime;
	}
	@Column(name="Regulator_Kind",nullable = false, length = 100)
	public String getRegulatorKind() {
		return regulatorKind;
	}
	public void setRegulatorKind(String regulatorKind) {
		this.regulatorKind = regulatorKind;
	}
	public void setUseStartTime(Calendar useStartTime) {
		this.useStartTime = useStartTime;
	}
	@Column(name="use_End_Time",nullable = false, length = 100)
	public Calendar getUseEndTime() {
		return useEndTime;
	}
	public void setUseEndTime(Calendar useEndTime) {
		this.useEndTime = useEndTime;
	}
	@Column(name="use_Method",nullable = false, length = 100)
	public String getUseMethod() {
		return useMethod;
	}
	public void setUseMethod(String useMethod) {
		this.useMethod = useMethod;
	}
	@Column(name="num",nullable = false, length = 100)
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	@Column(name="unit",nullable = false, length = 100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="enroll_No",nullable = false, length = 100)
	public String getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	@Column(name="company",nullable = false, length = 100)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name="produce_Date",nullable = false, length = 100)
	public Calendar getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Calendar produceDate) {
		this.produceDate = produceDate;
	}
	
	@Column(name="status",nullable = false, length = 100)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="purchase_Date",nullable = false, length = 100)
	public Calendar getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Calendar purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
}
