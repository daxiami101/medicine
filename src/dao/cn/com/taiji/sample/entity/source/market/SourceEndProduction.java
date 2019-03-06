package cn.com.taiji.sample.entity.source.market;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 成品管理
 */
@Entity
@Table(name = "source_end_production")
public class SourceEndProduction extends AbstractInsertTimeEntity
{
	private String purchaseNo;//原药材批号
	private String makeNo;//炮制批号
	private String extractNo;//提取批号
	private String preparationNo;//制剂编号
	private String caseNo;//包装（产品）批号：(非常重要)
	private String workplace;//生产车间（勾选项）：
	private Double dose;//领料量（提取液）：  （kg）
	private Calendar doseDate;//领料时间： 年 月 日
	
	private String endProductionNo;//产品批号：
	private Calendar storeDate;//入库时间： 年 月 日
	private Double storeNum;//入库数量：
	private String warehouseNo;// 仓库代码：
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
	@Column(name = "end_Production_No", nullable = false, length = 100)
	public String getEndProductionNo() {
		return endProductionNo;
	}
	public void setEndProductionNo(String endProductionNo) {
		this.endProductionNo = endProductionNo;
	}
	@Column(name = "store_Date", nullable = false, length = 100)
	public Calendar getStoreDate() {
		return storeDate;
	}
	public void setStoreDate(Calendar storeDate) {
		this.storeDate = storeDate;
	}
	@Column(name = "store_Num", nullable = false, length = 100)
	public Double getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(Double storeNum) {
		this.storeNum = storeNum;
	}
	@Column(name = "warehouse_No", nullable = false, length = 100)
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	
}
