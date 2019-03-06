package cn.com.taiji.sample.repo.jpa.source;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.sample.entity.source.SourceCounty;

public interface SourceCountyRepo extends AbstractJpaRepo<SourceCounty, String>
{
	@Query(" from SourceCounty where cityId=?1")
	public List<SourceCounty>listByCityId(String cityId);
}
