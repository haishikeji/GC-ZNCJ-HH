package com.pc.biz.service.impl;

import com.pc.biz.dao.ProduceOutputDao;
import com.pc.biz.domain.ProduceOutputDO;
import com.pc.biz.service.ProduceOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class ProduceOutputServiceImpl implements ProduceOutputService {
	@Autowired
	private ProduceOutputDao produceOutputDao;

	@Override
	public ProduceOutputDO getByIp(String sbIp){
		return produceOutputDao.getByIp(sbIp);
	}

	@Override
	public ProduceOutputDO getByIpDate(String sbIp, Date updateTime){
		return produceOutputDao.getByIpDate(sbIp,updateTime);
	}

	@Override
	public ProduceOutputDO get(Long id){
		return produceOutputDao.get(id);
	}

	@Override
	public List<ProduceOutputDO> list(Map<String, Object> map){
		return produceOutputDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return produceOutputDao.count(map);
	}

	@Override
	public int save(ProduceOutputDO produceOutput){
		return produceOutputDao.save(produceOutput);
	}

	@Override
	public int update(ProduceOutputDO produceOutput){
		return produceOutputDao.update(produceOutput);
	}

	@Override
	public int remove(Long id){
		return produceOutputDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return produceOutputDao.batchRemove(ids);
	}

	@Override
	public int getProduceNum(Map<String, Object> map){
		return  produceOutputDao.getProduceNum(map);
	}
}
