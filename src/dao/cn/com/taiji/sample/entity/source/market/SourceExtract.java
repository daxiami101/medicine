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
 * 提取管理
 */
@Entity
@Table(name = "source_extract")
public class SourceExtract extends AbstractInsertTimeEntity
{
	private String purchaseNo;//原药材批号
	private String makeNo;//炮制批号
	private String extractNo;//提取批号
	private String workplace;//生产车间（勾选项）：
	private Double dose;//领料量：  （kg）
	private Calendar doseDate;//领料时间： 年 月 日
	private String technique;//工艺：
	private Double feedNum;//投料量：  （kg）
	private Double extractNum;//提取量：  （kg）
	private Double condenseNum;//浓缩量：   （kg）
	private Double dryNum;//干燥量：   （kg）
	private Calendar extractDate;//提取时间： 年 月 日
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
	@Column(name = "extract_Num", nullable = false, length = 100)
	public Double getExtractNum() {
		return extractNum;
	}
	public void setExtractNum(Double extractNum) {
		this.extractNum = extractNum;
	}
	@Column(name = "condense_Num", nullable = false, length = 100)
	public Double getCondenseNum() {
		return condenseNum;
	}
	public void setCondenseNum(Double condenseNum) {
		this.condenseNum = condenseNum;
	}
	@Column(name = "dry_Num", nullable = false, length = 100)
	public Double getDryNum() {
		return dryNum;
	}
	public void setDryNum(Double dryNum) {
		this.dryNum = dryNum;
	}
	@Column(name = "extract_Date", nullable = false, length = 100)
	public Calendar getExtractDate() {
		return extractDate;
	}
	public void setExtractDate(Calendar extractDate) {
		this.extractDate = extractDate;
	}
	
}
