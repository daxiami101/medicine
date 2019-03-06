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
 * 制剂管理
 */
@Entity
@Table(name = "source_preparation")
public class SourcePreparation extends AbstractInsertTimeEntity
{
	private String purchaseNo;//原药材批号
	private String makeNo;//炮制批号
	private String extractNo;//提取批号
	private String preparationNo;//制剂编号
	private String workplace;//生产车间（勾选项）：
	private Double dose;//领料量（提取液）：  （kg）
	private Calendar doseDate;//领料时间： 年 月 日
	private String accessoryKind;//辅料种类（勾选项、可多选）：
	private String accessoryNote;//辅料用量（对应多种辅料种类数量）：  （kg）
	private String technique;//工艺：
	private Double feedNum;//投料量：（kg）
	private Double output;//产出量：（kg）
	private Calendar preparationDate;//制剂时间： 年 月 日
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
	@Column(name = "accessory_Kind", nullable = false, length = 100)
	public String getAccessoryKind() {
		return accessoryKind;
	}
	public void setAccessoryKind(String accessoryKind) {
		this.accessoryKind = accessoryKind;
	}
	@Column(name = "accessory_Note", nullable = false, length = 100)
	public String getAccessoryNote() {
		return accessoryNote;
	}
	public void setAccessoryNote(String accessoryNote) {
		this.accessoryNote = accessoryNote;
	}
	@Column(name = "technique", nullable = false, length = 100)
	public String getTechnique() {
		return technique;
	}
	public void setTechnique(String technique) {
		this.technique = technique;
	}
	@Column(name = "feed_Num", nullable = false, length = 100)
	public Double getFeedNum() {
		return feedNum;
	}
	public void setFeedNum(Double feedNum) {
		this.feedNum = feedNum;
	}
	@Column(name = "output", nullable = false, length = 100)
	public Double getOutput() {
		return output;
	}
	public void setOutput(Double output) {
		this.output = output;
	}
	@Column(name = "preparation_Date", nullable = false, length = 100)
	public Calendar getPreparationDate() {
		return preparationDate;
	}
	public void setPreparationDate(Calendar preparationDate) {
		this.preparationDate = preparationDate;
	}
	
}
