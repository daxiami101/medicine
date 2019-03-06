package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 成品管理
 */
@Entity
@Table(name = "source_production")
public class SourceProduction extends AbstractInsertTimeEntity
{
	private String materialName;//产品名称
	private String originalPlace;//产地
	private String productionId;//产品批号
	private String standard;//产品包装规格
	private String unit;//计量单位
	private String outNum;//出库数量(个)
	private String leftNum;//库存
	private Calendar outTime ;//*出库日期
	private String storeMethod;
	private String dataSource;//数据源
	private String packageNo;//包装批号
	private String packageId;//包装id
	@Column(name="package_No",nullable = false, length = 100)
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	@Column(name="package_Id",nullable = false, length = 100)
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	@Column(name="left_Num",nullable = false, length = 100)
	public String getLeftNum() {
		return leftNum;
	}
	public void setLeftNum(String leftNum) {
		this.leftNum = leftNum;
	}
	@Column(name="data_Source",nullable = false, length = 100)
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
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
	@Column(name="production_Id",nullable = false, length = 100)
	public String getProductionId() {
		return productionId;
	}
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
	@Column(name="standard",nullable = false, length = 100)
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Column(name="unit",nullable = false, length = 100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="out_Num",nullable = false, length = 100)
	public String getOutNum() {
		return outNum;
	}
	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}
	@Column(name="out_Time",nullable = false, length = 100)
	public Calendar getOutTime() {
		return outTime;
	}
	public void setOutTime(Calendar outTime) {
		this.outTime = outTime;
	}
	@Column(name="store_Method",nullable = false, length = 100)
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}//存储方式
}
