package com.pc.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pc.common.utils.Query;
import com.pc.system.dao.SbtypeDao;
import com.pc.system.domain.SbtypeDO;
import com.pc.system.service.SbtypeService;



@Service
public class SbtypeServiceImpl implements SbtypeService {
	@Autowired
	private SbtypeDao sbtypeDao;
	
	@Override
	public SbtypeDO get(Integer id){
		return sbtypeDao.get(id);
	}
	
	@Override
	public List<SbtypeDO> list(Map<String, Object> map){
		return sbtypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sbtypeDao.count(map);
	}
	
	@Override
	public int countFindList(Map<String, Object> map){
		return sbtypeDao.countFindList(map);
	}

	@Override
	public int save(SbtypeDO sbtype){
		return sbtypeDao.save(sbtype);
	}
	
	@Override
	public int update(SbtypeDO sbtype){
		return sbtypeDao.update(sbtype);
	}
	
	@Override
	public int remove(Integer id){
		return sbtypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return sbtypeDao.batchRemove(ids);
	}

	@Override
	public List<SbtypeDO> getAllTime() {
		return sbtypeDao.getAllTime();
	}

	@Override
	public int updateType(SbtypeDO sbtypeTime) {
		return sbtypeDao.updateType(sbtypeTime);
		
	}

	@Override
	public SbtypeDO listMoneth(Map<String, Object> queryMoneth) {
		return sbtypeDao.listMoneth(queryMoneth);
	}

	@Override
	public List<SbtypeDO> findList(Map<String, Object> map) {
		return sbtypeDao.findList(map);
	}

	@Override
	public List<SbtypeDO> listTuRe(HashMap<String, Object> query) {
		return sbtypeDao.listTuRe(query);
	}

	@Override
	public List<SbtypeDO> listTuYue(HashMap<String, Object> query2) {
		return sbtypeDao.listTuYue(query2);
	}

	@Override
	public List<SbtypeDO> listHistory(Query query) {
		return sbtypeDao.listHistory(query);
	}

	@Override
	public int countHistory(Query query) {
		return sbtypeDao.countHistory(query);
	}

	@Override
	public List<SbtypeDO> listHistory(Map<String, Object> params) {
		return sbtypeDao.listHistory(params);
	}

	@Override
	public int saveType(SbtypeDO startTime) {
		return	sbtypeDao.saveType(startTime);
		
	}

	@Override
	public List<SbtypeDO> getAllThing() {
		return sbtypeDao.getAllThing();
	}

	
}
