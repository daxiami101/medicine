package cn.com.taiji.sample.entity.source.market;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 经销商信息
 */
@Entity
@Table(name = "source_enterprise")
public class SourceEnterprise extends AbstractInsertTimeEntity
{
	private String name;//企业名称
	private Double purchaseOneTime;//一次性购进量
	@Column(name="purchase_One_Time")
	public Double getPurchaseOneTime() {
		return purchaseOneTime;
	}
	public void setPurchaseOneTime(Double purchaseOneTime) {
		this.purchaseOneTime = purchaseOneTime;
	}
	@Column(name="name",nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
