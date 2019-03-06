package cn.com.taiji.sample.entity.source.market;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;

/**
 * 
 * @author admin02 2018年9月2日 下午3:29:53 包装管理
 */
@Entity
@Table(name = "source_case")
public class SourceCase extends AbstractInsertTimeEntity {
	private String purchaseNo;// 原药材批号
	private String makeNo;// 炮制批号
	private String extractNo;// 提取批号
	private String preparationNo;// 制剂编号
	private String caseNo;// 包装（产品）批号：(非常重要)
	private String workplace;// 生产车间（勾选项）：
	private Double dose;// 领料量（提取液）： （kg）
	private Calendar doseDate;// 领料时间： 年 月 日

	private Double inMaterialNum;// 内包材材质：（勾选项）所用包材数量：
	private String inMaterial;// 内包材质：（勾选项）

	private Double midMaterialNum;// 中包所用包材数量：
	private String midMaterial;// 中包外包材质：（勾选项）

	private Double exMaterialNum;// 外包所用包材数量：
	private String exMaterial;// 外包材质：（勾选项）

	private Double caseNum;// 总共包装药品数量
	private String caseUnit;// 袋 盒 箱

	private Double packageNum;// 包装规格：每袋 g
	private Double packageUnit;// 包装规格：每袋 g

	private Calendar caseDate;// 包装时间： 年 月 日

	@Column(name = "purchase_No", nullable = false, length = 100)
	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	@Column(name = "make_No", nullable = false, length = 100)
	public String getMakeNo() {
		return makeNo;
	}

	public void setMakeNo(String makeNo) {
		this.makeNo = makeNo;
	}

	@Column(name = "extract_No", nullable = false, length = 100)
	public String getExtractNo() {
		return extractNo;
	}

	public void setExtractNo(String extractNo) {
		this.extractNo = extractNo;
	}

	@Column(name = "preparation_No", nullable = false, length = 100)
	public String getPreparationNo() {
		return preparationNo;
	}

	public void setPreparationNo(String preparationNo) {
		this.preparationNo = preparationNo;
	}

	@Column(name = "case_No", nullable = false, length = 100)
	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	@Column(name = "workplace", nullable = false, length = 100)
	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	@Column(name = "dose", nullable = false, length = 100)
	public Double getDose() {
		return dose;
	}

	public void setDose(Double dose) {
		this.dose = dose;
	}

	@Column(name = "dose_Date", nullable = false, length = 100)
	public Calendar getDoseDate() {
		return doseDate;
	}

	public void setDoseDate(Calendar doseDate) {
		this.doseDate = doseDate;
	}

	@Column(name = "in_Material_Num", nullable = false, length = 100)
	public Double getInMaterialNum() {
		return inMaterialNum;
	}

	public void setInMaterialNum(Double inMaterialNum) {
		this.inMaterialNum = inMaterialNum;
	}

	@Column(name = "in_Material", nullable = false, length = 100)
	public String getInMaterial() {
		return inMaterial;
	}

	public void setInMaterial(String inMaterial) {
		this.inMaterial = inMaterial;
	}

	@Column(name = "mid_Material_Num", nullable = false, length = 100)
	public Double getMidMaterialNum() {
		return midMaterialNum;
	}

	public void setMidMaterialNum(Double midMaterialNum) {
		this.midMaterialNum = midMaterialNum;
	}

	@Column(name = "mid_Material", nullable = false, length = 100)
	public String getMidMaterial() {
		return midMaterial;
	}

	public void setMidMaterial(String midMaterial) {
		this.midMaterial = midMaterial;
	}

	@Column(name = "ex_Material_Num", nullable = false, length = 100)
	public Double getExMaterialNum() {
		return exMaterialNum;
	}

	public void setExMaterialNum(Double exMaterialNum) {
		this.exMaterialNum = exMaterialNum;
	}

	@Column(name = "case_Num", nullable = false, length = 100)
	public Double getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(Double caseNum) {
		this.caseNum = caseNum;
	}

	@Column(name = "case_Unit", nullable = false, length = 100)
	public String getCaseUnit() {
		return caseUnit;
	}

	public void setCaseUnit(String caseUnit) {
		this.caseUnit = caseUnit;
	}

	@Column(name = "package_Num", nullable = false, length = 100)
	public Double getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Double packageNum) {
		this.packageNum = packageNum;
	}

	@Column(name = "package_Unit", nullable = false, length = 100)
	public Double getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(Double packageUnit) {
		this.packageUnit = packageUnit;
	}

	@Column(name = "case_Date", nullable = false, length = 100)
	public Calendar getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(Calendar caseDate) {
		this.caseDate = caseDate;
	}

	@Column(name = "ex_Material", nullable = false, length = 100)
	public String getExMaterial() {
		return exMaterial;
	}

	public void setExMaterial(String exMaterial) {
		this.exMaterial = exMaterial;
	}

}
