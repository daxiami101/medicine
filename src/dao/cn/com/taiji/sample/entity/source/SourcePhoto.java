package cn.com.taiji.sample.entity.source;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 照片信息
 */
@Entity
@Table(name = "source_photo")
public class SourcePhoto extends AbstractInsertTimeEntity
{
	@NotNull
	private String oriId;//照片原始ID
	@NotNull
	private Blob photo;//照片
	@NotNull
	private String sysName;
	@Column(name="ori_Id")
	public String getOriId() {
		return oriId;
	}
	public void setOriId(String oriId) {
		this.oriId = oriId;
	}
	@Column(name="photo")
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	@Column(name="sys_Name")
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
}
