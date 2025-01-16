package com.pc.system.dao;

import com.pc.system.domain.ShebeiDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
@Mapper
public interface ShebeiDao {

	ShebeiDO get(Integer id);
	
	List<ShebeiDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ShebeiDO shebei);
	
	int update(ShebeiDO shebei);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	int getNum(String sbNumber);

	int getIp(String ipAddress);

	List<ShebeiDO> getAll();
}
