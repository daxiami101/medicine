package cn.com.taiji.sample.entity.source;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 饮片生产
 */
@Entity
@Table(name = "source_medicine_produce")
public class SourceMedicineProduce extends AbstractInsertTimeEntity
{
	private String materialName;//药材名称
	private String originalPlace;//产地
	private String medicineNo;//药材批号
	private String standard;//规格
	private Double leftNum;//库存数量(个)
	private String executeStandard;//*执行标准
	private String productionName;//*产品名称
	private String medicineCode;//饮片编码
	private String manufactureMethod;//炮制方法
	private String processStandard;//饮片加工规格
	private String unit;//计量单位
	private String produceId;//*产品批号
	private Double amount;//领用量(kg)
	private LocalDateTime produceTime ;//*生产日期
	private Double productionNum;//生产数量
	private String purchaseId;//采购id
	private String purchaseNo;//物料批号
	@Column(name="purchase_No",nullable = false, length = 100)
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	@Column(name="purchase_Id",nullable = false, length = 100)
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	@Column(name="material_Name",nullable = false, length = 100)
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	@Column(name="original_Place",nullable = false, length = 100)
	public String getOriginalPlace() {
		return originalPlace;
	}
	public void setOriginalPlace(String originalPlace) {
		this.originalPlace = originalPlace;
	}
	
	
	@Column(name="standard",nullable = false, length = 100)
	public String getStandard() {
		return standard;
	}
	@Column(name="medicine_No",nullable = false, length = 100)
	public String getMedicineNo() {
		return medicineNo;
	}
	public void setMedicineNo(String medicineNo) {
		this.medicineNo = medicineNo;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Column(name="left_Num",nullable = false, length = 100)
	public Double getLeftNum() {
		return leftNum;
	}
	public void setLeftNum(Double leftNum) {
		this.leftNum = leftNum;
	}
	@Column(name="execute_Standard",nullable = false, length = 100)
	public String getExecuteStandard() {
		return executeStandard;
	}
	public void setExecuteStandard(String executeStandard) {
		this.executeStandard = executeStandard;
	}
	@Column(name="production_Name",nullable = false, length = 100)
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	@Column(name="medicine_Code",nullable = false, length = 100)
	public String getMedicineCode() {
		return medicineCode;
	}
	
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	@Column(name="manufacture_Method",nullable = false, length = 100)
	public String getManufactureMethod() {
		return manufactureMethod;
	}
	public void setManufactureMethod(String manufactureMethod) {
		this.manufactureMethod = manufactureMethod;
	}
	@Column(name="process_Standard",nullable = false, length = 100)
	public String getProcessStandard() {
		return processStandard;
	}
	public void setProcessStandard(String processStandard) {
		this.processStandard = processStandard;
	}
	@Column(name="produce_Id",nullable = false, length = 100)
	public String getProduceId() {
		return produceId;
	}
	public void setProduceId(String produceId) {
		this.produceId = produceId;
	}
	@Column(name="amount",nullable = false, length = 100)
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Column(name="produce_Time",nullable = false, length = 100)
	public LocalDateTime getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(LocalDateTime produceTime) {
		this.produceTime = produceTime;
	}
	@Column(name="production_Num",nullable = false, length = 100)
	public Double getProductionNum() {
		return productionNum;
	}
	public void setProductionNum(Double productionNum) {
		this.productionNum = productionNum;
	}//成品数量(个)
	@Column(name="unit",nullable = false, length = 100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
