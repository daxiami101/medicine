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
 * 炮制管理
 */
@Entity
@Table(name = "source_make")
public class SourceMake extends AbstractInsertTimeEntity
{
	private String purchaseNo;//原药材批号
	private String makeNo;//炮制批号
	private String workplace;//生产车间（勾选项）：
	private Double dose;//领料量：  （吨）
	private Calendar doseDate;//领料时间： 年 月 日
	private String method;//炮制：方法：
	private String accessory;//辅料：
	private Double num;//炮制数量：  （吨）
	private Calendar makeDate;//炮制时间： 年 月 日
	@Column(name="purchase_No")
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	@Column(name="make_No")
	public String getMakeNo() {
		return makeNo;
	}
	public void setMakeNo(String makeNo) {
		this.makeNo = makeNo;
	}
	@Column(name="workplace")
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	@Column(name="dose")
	public Double getDose() {
		return dose;
	}
	public void setDose(Double dose) {
		this.dose = dose;
	}
	@Column(name="dose_Date")
	public Calendar getDoseDate() {
		return doseDate;
	}
	public void setDoseDate(Calendar doseDate) {
		this.doseDate = doseDate;
	}
	@Column(name="method")
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Column(name="accessory")
	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	@Column(name="num")
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	@Column(name="make_Date")
	public Calendar getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(Calendar makeDate) {
		this.makeDate = makeDate;
	}
}
