package cn.com.taiji.sample.repo.jpa.source;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.sample.entity.source.SourcePhoto;

public interface SourcePhotoRepo extends AbstractJpaRepo<SourcePhoto, String>
{
	@Query("from SourcePhoto where 1=1 and oriId = ?1 ")
	public List<SourcePhoto> listByOriId(String oriId);
}
