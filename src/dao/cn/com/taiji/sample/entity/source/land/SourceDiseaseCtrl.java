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
 * 病害
 */
@Entity
@Table(name = "source_disease_ctrl")
public class SourceDiseaseCtrl extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String diseaseKind;//主要病害种类 	
	private String diseasePalce;//危害部位 	
	private Calendar preventStartTime;//防治开始时间 	
	private Calendar preventEndTime;//防治结束时间 	
	private String preventMethod;//防治方式 	
	private String drugKind;//农药种类 	
	private Double num;//用药量/亩 	
	private String unit;//计量单位 	
	private String enrollNo;//农药登记证号 	
	private String company;//农药生产厂家 	
	private Calendar produceDate;//农药生产日期 	
	private Calendar purchaseDate;//农药采购日期
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
	@Column(name="disease_Kind",nullable = false, length = 100)
	public String getDiseaseKind() {
		return diseaseKind;
	}
	public void setDiseaseKind(String diseaseKind) {
		this.diseaseKind = diseaseKind;
	}
	@Column(name="disease_Palce",nullable = false, length = 100)
	public String getDiseasePalce() {
		return diseasePalce;
	}
	public void setDiseasePalce(String diseasePalce) {
		this.diseasePalce = diseasePalce;
	}
	@Column(name="prevent_Start_Time",nullable = false, length = 100)
	public Calendar getPreventStartTime() {
		return preventStartTime;
	}
	public void setPreventStartTime(Calendar preventStartTime) {
		this.preventStartTime = preventStartTime;
	}
	@Column(name="prevent_End_Time",nullable = false, length = 100)
	public Calendar getPreventEndTime() {
		return preventEndTime;
	}
	public void setPreventEndTime(Calendar preventEndTime) {
		this.preventEndTime = preventEndTime;
	}
	@Column(name="prevent_Method",nullable = false, length = 100)
	public String getPreventMethod() {
		return preventMethod;
	}
	public void setPreventMethod(String preventMethod) {
		this.preventMethod = preventMethod;
	}
	@Column(name="drug_Kind",nullable = false, length = 100)
	public String getDrugKind() {
		return drugKind;
	}
	public void setDrugKind(String drugKind) {
		this.drugKind = drugKind;
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
	@Column(name="purchase_Date",nullable = false, length = 100)
	public Calendar getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Calendar purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@Column(name="status",nullable = false, length = 100)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
}
