package com.pc.biz.service;

import com.pc.biz.domain.AbnormalReasonDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
public interface AbnormalReasonService {
	
	AbnormalReasonDO get(Integer id);
	
	List<AbnormalReasonDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AbnormalReasonDO abnormalReason);
	
	int update(AbnormalReasonDO abnormalReason);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
