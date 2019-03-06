package cn.com.taiji.sample.manager.medicine.media;

import java.util.List;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePhoto;
import cn.com.taiji.sample.repo.request.source.SourcePhotoPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * 照片管理
 */
public interface SourcePhotoManager
{
	public Pagination queryPage(SourcePhotoPageRequest req);

	public void delete(String id);

	public List<SourcePhoto> listByOriId(String oriId);
	public SourcePhoto findById(String id);

	public void save(SourcePhoto photo);

}
