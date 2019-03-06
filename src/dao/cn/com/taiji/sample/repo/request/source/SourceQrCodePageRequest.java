/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceQrCode;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceQrCodePageRequest extends JpaPageableDataRequest<SourceQrCode>
{
	private String url;//溯源信息url,不能变
	private String uniqueItem;//唯一标识
	private String medicineName;
	
	public SourceQrCodePageRequest()
	{
		this.orderBy = "insertTime";
		this.desc =true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and medicineName like :medicineName", like(medicineName));
		hql.append(" and url like :url", like(url));
		hql.append(" and uniqueItem like :uniqueItem", like(uniqueItem));
		return hql;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUniqueItem() {
		return uniqueItem;
	}
	public void setUniqueItem(String uniqueItem) {
		this.uniqueItem = uniqueItem;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
}
