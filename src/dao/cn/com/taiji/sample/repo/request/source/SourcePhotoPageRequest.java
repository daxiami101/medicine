/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourcePhoto;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * 照片管理
 */
public class SourcePhotoPageRequest extends JpaPageableDataRequest<SourcePhoto>
{
	private String oriId;//照片原始ID
	private String sysName;
	private String dataSource;
	
	public SourcePhotoPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc=true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and oriId = :oriId", oriId);
		hql.append(" and sysName like :sysName", like(sysName));
		hql.append(" and dataSource=:dataSource", dataSource);
		return hql;
	}
	public String getOriId() {
		return oriId;
	}
	public void setOriId(String oriId) {
		this.oriId = oriId;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
}
