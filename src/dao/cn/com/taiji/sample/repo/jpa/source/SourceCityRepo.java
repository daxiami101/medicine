package cn.com.taiji.sample.repo.jpa.source;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.sample.entity.source.SourceCity;

public interface SourceCityRepo extends AbstractJpaRepo<SourceCity, String>
{
	@Query(" from SourceCity where provinceId=?1")
	public List<SourceCity>listByProvinceId(String provinceId);
}
