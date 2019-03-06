package cn.com.taiji.sample.entity.source.land;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 虫害
 */
@Entity
@Table(name = "source_pest_ctrl")
public class SourcePestCtrl extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String pestKind;//主要虫害种类 	
	private String pestPlace;//危害部位 	
	private LocalDateTime preventStartTime;//防治开始时间 	
	private LocalDateTime preventEndTime;//防治结束时间 	
	private String preventMethod;//防治方式 	
	private String pesticideKind;//农药种类 	
	private String num;//用药量/亩 	
	private String unit;//计量单位 	
	private String enrollNo;//农药登记证号 	
	private String company;//农药生产厂家 	
	private LocalDateTime produceDate;//农药生产日期 	
	private LocalDateTime purchaseDate;//农药采购日期 	
	private String purchasePersonName;//农药采购人员
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
	@Column(name="pest_Kind",nullable = false, length = 100)
	public String getPestKind() {
		return pestKind;
	}
	public void setPestKind(String pestKind) {
		this.pestKind = pestKind;
	}
	@Column(name="pest_Place",nullable = false, length = 100)
	public String getPestPlace() {
		return pestPlace;
	}
	public void setPestPlace(String pestPlace) {
		this.pestPlace = pestPlace;
	}
	@Column(name="prevent_Start_Time",nullable = false, length = 100)
	public LocalDateTime getPreventStartTime() {
		return preventStartTime;
	}
	public void setPreventStartTime(LocalDateTime preventStartTime) {
		this.preventStartTime = preventStartTime;
	}
	@Column(name="prevent_End_Time",nullable = false, length = 100)
	public LocalDateTime getPreventEndTime() {
		return preventEndTime;
	}
	public void setPreventEndTime(LocalDateTime preventEndTime) {
		this.preventEndTime = preventEndTime;
	}
	@Column(name="prevent_Method",nullable = false, length = 100)
	public String getPreventMethod() {
		return preventMethod;
	}
	public void setPreventMethod(String preventMethod) {
		this.preventMethod = preventMethod;
	}
	@Column(name="pesticide_Kind",nullable = false, length = 100)
	public String getPesticideKind() {
		return pesticideKind;
	}
	public void setPesticideKind(String pesticideKind) {
		this.pesticideKind = pesticideKind;
	}
	@Column(name="num",nullable = false, length = 100)
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
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
	public LocalDateTime getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(LocalDateTime produceDate) {
		this.produceDate = produceDate;
	}
	@Column(name="purchase_Date",nullable = false, length = 100)
	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@Column(name="purchase_Person_Name",nullable = false, length = 100)
	public String getPurchasePersonName() {
		return purchasePersonName;
	}
	public void setPurchasePersonName(String purchasePersonName) {
		this.purchasePersonName = purchasePersonName;
	}
	@Column(name="status",nullable = false, length = 100)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
