package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.PackageStatus;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 种源管理
 */
@Entity
@Table(name = "source_wait_package")
public class SourceWaitPackage extends AbstractInsertTimeEntity
{
	private String produceName;//产品名称
	private String originalPlace;//产地
	private String productionId;//产品批号
	private Calendar packageTime ;//*分装日期
	private String unit;//计量单位
	private String standard;//包装规格
	private String num;//数量(个)
	private String comcode;//企业编码
	private String medicineCode;//
	private PackageStatus packageStatus;//待分装状态
	private String packageNo;//包装批号
	@Column(name="package_No",nullable = false, length = 100)
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="package_Status",nullable = false, length = 100)
	public PackageStatus getPackageStatus() {
		return packageStatus;
	}
	public void setPackageStatus(PackageStatus packageStatus) {
		this.packageStatus = packageStatus;
	}
	@Column(name="produce_Name",nullable = false, length = 100)
	public String getProduceName() {
		return produceName;
	}
	public void setProduceName(String produceName) {
		this.produceName = produceName;
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
	@Column(name="package_Time",nullable = false, length = 100)
	public Calendar getPackageTime() {
		return packageTime;
	}
	public void setPackageTime(Calendar packageTime) {
		this.packageTime = packageTime;
	}
	@Column(name="unit",nullable = false, length = 100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="standard",nullable = false, length = 100)
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Column(name="num",nullable = false, length = 100)
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Column(name="comcode",nullable = false, length = 100)
	public String getComcode() {
		return comcode;
	}
	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	@Column(name="medicine_Code",nullable = false, length = 100)
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}//饮片编码
}
