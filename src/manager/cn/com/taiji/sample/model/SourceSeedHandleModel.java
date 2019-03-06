package cn.com.taiji.sample.model;

import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;

public class SourceSeedHandleModel extends SourceSeedHandle{
	private SourcePlantTask task;

	public SourcePlantTask getTask() {
		return task;
	}

	public void setTask(SourcePlantTask task) {
		this.task = task;
	}
}
