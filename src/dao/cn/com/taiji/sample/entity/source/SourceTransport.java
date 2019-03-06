package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.BuySell;
/**
 * 运输管理
 * @author admin02
 * 2018年10月17日 下午7:33:26
 * TODO
 */
@Entity
@Table(name = "source_transport")
public class SourceTransport extends AbstractInsertTimeEntity
{
	private String medicineName;//药材名称
	private String transMethod;//*运输方式
	private String securityMeasure;//安保措施
	private String securityContractCode;//*安保合同号
	private String medicineCode;//饮片编码
	private String companyOrderNo;//随货单据号
	private Calendar purchaseDate;//销售时间
	private Calendar transportTime  ;
	
	private String taskNo;
	private BuySell buySell;//数据源
	private String soldNo;//销售批号
	
	private String soldId;//销售id
	private String processId;//*加工批号
	private String harvestId;//采收id
	private String farmerId;//农户id
	private String seedId;//农户id
	private String processPackageId;//包装id
	private String plantTaskId;//种植任务id
	private String storeId;//存储id
	@Column(name="harvest_Id", length = 100)
	public String getHarvestId() {
		return harvestId;
	}
	public void setHarvestId(String harvestId) {
		this.harvestId = harvestId;
	}
	@Column(name="farmer_Id", length = 100)
	public String getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	@Column(name="seed_Id", length = 100)
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	@Column(name="process_Package_Id", length = 100)
	public String getProcessPackageId() {
		return processPackageId;
	}
	public void setProcessPackageId(String processPackageId) {
		this.processPackageId = processPackageId;
	}
	@Column(name="plant_Task_Id", length = 100)
	public String getPlantTaskId() {
		return plantTaskId;
	}
	public void setPlantTaskId(String plantTaskId) {
		this.plantTaskId = plantTaskId;
	}
	@Column(name="store_Id", length = 100)
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	@Column(name="sold_No", length = 100)
	public String getSoldNo() {
		return soldNo;
	}
	public void setSoldNo(String soldNo) {
		this.soldNo = soldNo;
	}
	@Column(name="sold_Id", length = 100)
	public String getSoldId() {
		return soldId;
	}
	public void setSoldId(String soldId) {
		this.soldId = soldId;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="buy_Sell", length = 100)
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
	}
	@Column(name="task_No", length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	@Column(name="medicine_Name", length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	@Column(name="process_Id", length = 100)
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	@Column(name="trans_Method", length = 100)
	public String getTransMethod() {
		return transMethod;
	}
	public void setTransMethod(String transMethod) {
		this.transMethod = transMethod;
	}
	@Column(name="security_Measure", length = 100)
	public String getSecurityMeasure() {
		return securityMeasure;
	}
	public void setSecurityMeasure(String securityMeasure) {
		this.securityMeasure = securityMeasure;
	}
	@Column(name="security_Contract_Code", length = 100)
	public String getSecurityContractCode() {
		return securityContractCode;
	}
	public void setSecurityContractCode(String securityContractCode) {
		this.securityContractCode = securityContractCode;
	}
	@Column(name="transport_Time", length = 100)
	public Calendar getTransportTime() {
		return transportTime;
	}
	public void setTransportTime(Calendar transportTime) {
		this.transportTime = transportTime;
	}//*运输时间
	@Column(name="medicine_Code", length = 100)
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	@Column(name="company_Order_No", length = 100)
	public String getCompanyOrderNo() {
		return companyOrderNo;
	}
	public void setCompanyOrderNo(String companyOrderNo) {
		this.companyOrderNo = companyOrderNo;
	}
	@Column(name="purchase_Date", length = 100)
	public Calendar getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Calendar purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
}
