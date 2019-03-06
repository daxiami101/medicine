package cn.com.taiji.sample.manager.tablet;

import javax.validation.Valid;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceQrCode;
import cn.com.taiji.sample.repo.request.source.SourceQrCodePageRequest;

/**
 * 二维码管理
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceQrCodeManager
{
	public Pagination queryPage(SourceQrCodePageRequest req);

	public void delete(String id);

	public Object findById(String id);

	public void save(@Valid SourceQrCode pageModel);

}
