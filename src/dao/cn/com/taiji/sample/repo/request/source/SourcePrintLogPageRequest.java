/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.time.LocalDateTime;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourcePrintLog;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourcePrintLogPageRequest extends JpaPageableDataRequest<SourcePrintLog>
{
private String productionName;//产品名称
	
	private String produceId;//产品批号
		
	private LocalDateTime printTime;//打印时间
	
	public SourcePrintLogPageRequest()
	{
//		this.orderBy = "namePy";
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and productionName like: productionName", like(productionName));
		hql.append(" and produceId=:produceId", produceId);
		return hql;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getProduceId() {
		return produceId;
	}
	public void setProduceId(String produceId) {
		this.produceId = produceId;
	}
	public LocalDateTime getPrintTime() {
		return printTime;
	}
	public void setPrintTime(LocalDateTime printTime) {
		this.printTime = printTime;
	}
}
