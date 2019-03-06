package cn.com.taiji.sample.manager.tablet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceQrCode;
import cn.com.taiji.sample.repo.jpa.source.SourceQrCodeRepo;
import cn.com.taiji.sample.repo.request.source.SourceQrCodePageRequest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午6:03:00
 * TODO
 */
@Service("sourceQrcodeManager")
public class SourceQrCodeManagerImpl extends AbstractManager implements SourceQrCodeManager{
	@Autowired
	private SourceQrCodeRepo repo;


	@Override
	public Pagination queryPage(SourceQrCodePageRequest req) {
		return repo.page(req);
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}


	@Override
	public Object findById(String id) {
		// TODO Auto-generated method stub
		return repo.getOne(id);
	}

	@Override
	public void save(@Valid SourceQrCode model) {
		repo.save(model);
	}


}
