package com.pc.system.service;

import com.pc.common.utils.Query;
import com.pc.system.domain.SearchMattersDO;

import java.util.List;
import java.util.Map;

/**
 * 热搜表
 * 
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
public interface SearchMattersService {
	
	SearchMattersDO get(String id);
	
	List<SearchMattersDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SearchMattersDO searchMatters);
	
	int update(SearchMattersDO searchMatters);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int getNumber(String bizMatterId);

	List<SearchMattersDO> listForHot();

	List<SearchMattersDO> listRanking();

	List<SearchMattersDO> listForHot1();

	int searchAdd(String id);

	int getCount();

	List<SearchMattersDO> list2(Query query);

	void update2(SearchMattersDO searchMatters);

	int remove2(String id);

	int count2(Query query);

	int save2(SearchMattersDO searchMatters);

	int count3();

	int getNumber2(String bizMatterId);
}
