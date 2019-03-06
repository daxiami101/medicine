package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 二维码管理
 */
@Entity
@Table(name = "source_qrcode")
public class SourceQrCode extends AbstractInsertTimeEntity
{
	private String url;//溯源信息url,不能变
	private String uniqueItem;//唯一标识
	private String medicineName;
	@Column(name="url",nullable = false, length = 100)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="unique_Item",nullable = false, length = 100)
	public String getUniqueItem() {
		return uniqueItem;
	}
	public void setUniqueItem(String uniqueItem) {
		this.uniqueItem = uniqueItem;
	}
	@Column(name="medicine_Name",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
}
