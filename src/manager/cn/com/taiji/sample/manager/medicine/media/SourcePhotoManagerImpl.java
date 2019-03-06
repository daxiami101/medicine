package cn.com.taiji.sample.manager.medicine.media;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePhoto;
import cn.com.taiji.sample.repo.jpa.source.SourcePhotoRepo;
import cn.com.taiji.sample.repo.request.source.SourcePhotoPageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * 照片管理
 */
@Service("sourcePhotoManager")
public class SourcePhotoManagerImpl extends AbstractManager implements SourcePhotoManager{
	@Autowired
	private SourcePhotoRepo repo;

	@Override
	public Pagination queryPage(SourcePhotoPageRequest req) {
		return repo.page(req);
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	@Override
	public List<SourcePhoto> listByOriId(String oriId) {
		return repo.listByOriId(oriId);
	}

	@Override
	public void save(SourcePhoto photo) {
		repo.save(photo);
	}

	@Override
	public SourcePhoto findById(String id) {
		return repo.findById(id).get();
	}
}
