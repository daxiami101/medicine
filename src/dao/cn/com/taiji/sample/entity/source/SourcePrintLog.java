package cn.com.taiji.sample.entity.source;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 种源管理
 */
@Entity
@Table(name = "source_print_log")
public class SourcePrintLog extends AbstractInsertTimeEntity
{
	private String productionName;//产品名称
	
	private String produceId;//产品批号
		
	private LocalDateTime printTime;//打印时间
	private String user;//打印人
	@Column(name="production_Name",nullable = false, length = 100)
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	@Column(name="produce_Id",nullable = false, length = 100)
	public String getProduceId() {
		return produceId;
	}
	public void setProduceId(String produceId) {
		this.produceId = produceId;
	}
	@Column(name="print_Time",nullable = false, length = 100)
	public LocalDateTime getPrintTime() {
		return printTime;
	}
	public void setPrintTime(LocalDateTime printTime) {
		this.printTime = printTime;
	}
	@Column(name="user",nullable = false, length = 100)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}//打印人
}
