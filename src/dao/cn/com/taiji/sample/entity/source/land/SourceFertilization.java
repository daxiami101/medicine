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
 * 灌溉
 */
@Entity
@Table(name = "source_Fertilization")
public class SourceFertilization extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String taskNo;//种植批号
	private String fertilitizationKind;//肥料种类</th>
	private Calendar fertStartTime;//<th>施肥开始时间 </th>
	private Calendar fertEndTime;//<th >施肥结束时间 </th>
	private String fertMethod;//<th>施肥方式 </th>
	private Double fertNum;//<th>施肥量/亩</th>
	private String unit;//<th>计量单位</th>
	private String enrollNo;//<th>登记证号 </th>
	private String produceCom;//<th>生产厂家  </th>
	private Calendar produceDate;//<th>生产日期  </th>
	private Calendar purchaseDate;//<th>采购日期</th>
	private String status;
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
	@Column(name="fertilitization_Kind",nullable = false, length = 100)
	public String getFertilitizationKind() {
		return fertilitizationKind;
	}
	public void setFertilitizationKind(String fertilitizationKind) {
		this.fertilitizationKind = fertilitizationKind;
	}
	@Column(name="fert_Start_Time",nullable = false, length = 100)
	public Calendar getFertStartTime() {
		return fertStartTime;
	}
	public void setFertStartTime(Calendar fertStartTime) {
		this.fertStartTime = fertStartTime;
	}
	@Column(name="fert_End_Time",nullable = false, length = 100)
	public Calendar getFertEndTime() {
		return fertEndTime;
	}
	public void setFertEndTime(Calendar fertEndTime) {
		this.fertEndTime = fertEndTime;
	}
	@Column(name="fert_Method",nullable = false, length = 100)
	public String getFertMethod() {
		return fertMethod;
	}
	public void setFertMethod(String fertMethod) {
		this.fertMethod = fertMethod;
	}
	@Column(name="fert_Num",nullable = false, length = 100)
	public Double getFertNum() {
		return fertNum;
	}
	public void setFertNum(Double fertNum) {
		this.fertNum = fertNum;
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
	@Column(name="produce_Com",nullable = false, length = 100)
	public String getProduceCom() {
		return produceCom;
	}
	public void setProduceCom(String produceCom) {
		this.produceCom = produceCom;
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
	}//审核状态
	@Column(name="task_No",nullable = false, length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
}
