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
 * 除草
 */
@Entity
@Table(name = "source_weed")
public class SourceWeed extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String weedKind;//主要杂草种类
	private Calendar weedStartTime;//除草开始时间 	
	private Calendar weedEndTime ;//除草结束时间 
	private String weedMethod;//除草方式 	
	private String weedicide;//除草剂种类 	
	private Double num;//用药量/亩 	
	private String unit;//计量单位
	private String weedicideNo;//农药登记证号
	private String weedCom;//农药生产厂家 	
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
	@Column(name="weed_Kind",nullable = false, length = 100)
	public String getWeedKind() {
		return weedKind;
	}
	public void setWeedKind(String weedKind) {
		this.weedKind = weedKind;
	}
	@Column(name="weed_Start_Time",nullable = false, length = 100)
	public Calendar getWeedStartTime() {
		return weedStartTime;
	}
	public void setWeedStartTime(Calendar weedStartTime) {
		this.weedStartTime = weedStartTime;
	}
	@Column(name="weed_End_Time",nullable = false, length = 100)
	public Calendar getWeedEndTime() {
		return weedEndTime;
	}
	public void setWeedEndTime(Calendar weedEndTime) {
		this.weedEndTime = weedEndTime;
	}
	@Column(name="weed_Method",nullable = false, length = 100)
	public String getWeedMethod() {
		return weedMethod;
	}
	public void setWeedMethod(String weedMethod) {
		this.weedMethod = weedMethod;
	}
	@Column(name="weedicide",nullable = false, length = 100)
	public String getWeedicide() {
		return weedicide;
	}
	public void setWeedicide(String weedicide) {
		this.weedicide = weedicide;
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
	@Column(name="weedicide_No",nullable = false, length = 100)
	public String getWeedicideNo() {
		return weedicideNo;
	}
	public void setWeedicideNo(String weedicideNo) {
		this.weedicideNo = weedicideNo;
	}
	@Column(name="weed_Com",nullable = false, length = 100)
	public String getWeedCom() {
		return weedCom;
	}
	public void setWeedCom(String weedCom) {
		this.weedCom = weedCom;
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
	
	
}
