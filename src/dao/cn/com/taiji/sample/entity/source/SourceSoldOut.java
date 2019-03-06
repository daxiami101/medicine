package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 销售出库
 */
@Entity
@Table(name = "source_Sold_Out")
public class SourceSoldOut extends AbstractInsertTimeEntity
{
	private String materialName;//产品名称
	private String originalPlace;//产地
	private String productionNo;//产品批号
	private String standard;//产品包装规格
	private String leftNum;//库存数量(个)
	private String unit;//计量单位
	private Double soldNum ;//销售数量(kg)
	private Calendar soldTime ;//*销售日期
	private String storeMethod;//存储方式
	private String companyOrderId;//随货同行单据号
	private String customServiceName;//客服姓名
	private String returnStatus;//退货状态
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
	@Column(name="production_No",nullable = false, length = 100)
	public String getProductionNo() {
		return productionNo;
	}
	public void setProductionNo(String productionNo) {
		this.productionNo = productionNo;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Column(name="left_Num",nullable = false, length = 100)
	public String getLeftNum() {
		return leftNum;
	}
	public void setLeftNum(String leftNum) {
		this.leftNum = leftNum;
	}
	@Column(name="unit",nullable = false, length = 100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="sold_Num",nullable = false, length = 100)
	public Double getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(Double soldNum) {
		this.soldNum = soldNum;
	}
	@Column(name="sold_Time",nullable = false, length = 100)
	public Calendar getSoldTime() {
		return soldTime;
	}
	public void setSoldTime(Calendar soldTime) {
		this.soldTime = soldTime;
	}
	@Column(name="store_Method",nullable = false, length = 100)
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}
	@Column(name="company_Order_Id",nullable = false, length = 100)
	public String getCompanyOrderId() {
		return companyOrderId;
	}
	public void setCompanyOrderId(String companyOrderId) {
		this.companyOrderId = companyOrderId;
	}
	@Column(name="custom_Service_Name",nullable = false, length = 100)
	public String getCustomServiceName() {
		return customServiceName;
	}
	public void setCustomServiceName(String customServiceName) {
		this.customServiceName = customServiceName;
	}//客服名称
	@Column(name="return_Status",nullable = false, length = 100)
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	
}
