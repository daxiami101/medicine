package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.BuySell;
/**
 * 销售管理
 * @author admin02
 * 2018年10月17日 下午7:32:43
 * TODO
 */
@Entity
@Table(name = "source_sold")
public class SourceSold extends AbstractInsertTimeEntity
{
	private String medicineName;//药材名称
	
	private String medicineCode;//药材编码
	private String commerceDepartCode;//商务部品种代码
	private String clientName;//客户名称
	private Calendar soldTime ;//*销售日期
	private Double soldNum ;//*销售数量(kg)
	private String barcode;//企业条形码
	private String taiwanCode;//台湾地区代码
	private String customCode;//海关码
	private String companyOrderId;//同行随货单号
	private String taskId;//种植任务
	private String taskNo;
	private String returnStatus;//退货状态
	private BuySell buySell;//数据源
	private String soldNo;//销售批号
	private String storeNo;//存储批号
	
	private String processId;//*加工批号
	private String harvestId;//采收id
	private String farmerId;//农户id
	private String seedId;//农户id
	private String processPackageId;//包装id
	private String plantTaskId;//种植任务idpn
	private String storeId;//存储id
	
	@Column(name="harvest_Id",nullable = false, length = 100)
	public String getHarvestId() {
		return harvestId;
	}
	public void setHarvestId(String harvestId) {
		this.harvestId = harvestId;
	}
	@Column(name="farmer_Id",nullable = false, length = 100)
	public String getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	@Column(name="seed_Id",nullable = false, length = 100)
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	@Column(name="process_Package_Id",nullable = false, length = 100)
	public String getProcessPackageId() {
		return processPackageId;
	}
	public void setProcessPackageId(String processPackageId) {
		this.processPackageId = processPackageId;
	}
	@Column(name="plant_Task_Id",nullable = false, length = 100)
	public String getPlantTaskId() {
		return plantTaskId;
	}
	public void setPlantTaskId(String plantTaskId) {
		this.plantTaskId = plantTaskId;
	}
	@Column(name="sold_No",nullable = false, length = 100)
	public String getSoldNo() {
		return soldNo;
	}
	public void setSoldNo(String soldNo) {
		this.soldNo = soldNo;
	}
	@Column(name="store_Id",nullable = false, length = 100)
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	@Column(name="store_No",nullable = false, length = 100)
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="buy_Sell",nullable = false, length = 100)
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
	}
	@Column(name="return_Status",nullable = false, length = 100)
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	@Column(name="task_No",nullable = false, length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	@Column(name="medicine_Name",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	@Column(name="process_Id",nullable = false, length = 100)
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	@Column(name="medicine_Code",nullable = false, length = 100)
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	@Column(name="commerce_Depart_Code",nullable = false, length = 100)
	public String getCommerceDepartCode() {
		return commerceDepartCode;
	}
	public void setCommerceDepartCode(String commerceDepartCode) {
		this.commerceDepartCode = commerceDepartCode;
	}
	@Column(name="client_Name",nullable = false, length = 100)
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Column(name="sold_Time",nullable = false, length = 100)
	public Calendar getSoldTime() {
		return soldTime;
	}
	public void setSoldTime(Calendar soldTime) {
		this.soldTime = soldTime;
	}
	@Column(name="sold_Num",nullable = false, length = 100)
	public Double getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(Double soldNum) {
		this.soldNum = soldNum;
	}
	@Column(name="barcode",nullable = false, length = 100)
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	@Column(name="taiwan_Code",nullable = false, length = 100)
	public String getTaiwanCode() {
		return taiwanCode;
	}
	public void setTaiwanCode(String taiwanCode) {
		this.taiwanCode = taiwanCode;
	}
	@Column(name="custom_Code",nullable = false, length = 100)
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	@Column(name="company_Order_Id",nullable = false, length = 100)
	public String getCompanyOrderId() {
		return companyOrderId;
	}
	public void setCompanyOrderId(String companyOrderId) {
		this.companyOrderId = companyOrderId;
	}
	
	
}
