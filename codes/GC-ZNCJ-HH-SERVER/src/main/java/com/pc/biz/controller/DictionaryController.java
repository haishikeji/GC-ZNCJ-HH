package com.pc.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pc.common.utils.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pc.common.constant.Constants;
import com.pc.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.biz.domain.DictionaryDO;
import com.pc.biz.service.DictionaryService;
import com.pc.common.utils.PageUtils;
import com.pc.common.utils.R;

/**
 * 
 * 
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
 
@Controller
@RequestMapping("/biz/dictionary")
public class DictionaryController extends BaseController {
	@Autowired
	private DictionaryService dictionaryService;
    private String prefix = "biz/dictionary";
	@GetMapping()
	@RequiresPermissions("biz:dictionary:dictionary")
	String Dictionary(){
	    return prefix+"/dictionary";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:dictionary:dictionary")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        startPage();
        List<DictionaryDO> list =  dictionaryService.list(params);
        PageUtils dataTable = getDataTable(list);
        return dataTable;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:dictionary:add")
	String add(){
	    return prefix+"/add";
	}

	@GetMapping("/edit/{id}")
    @RequiresPermissions("biz:dictionary:edit")
    String edit(@PathVariable("id") String id,Model model){
			DictionaryDO dictionary = dictionaryService.get(id);
        model.addAttribute("dictionary", dictionary);
        return prefix+"/edit";
    }
    @GetMapping("/view/{id}")
    @RequiresPermissions("biz:dictionary:dictionary")
    String view(@PathVariable("id") String id,Model model){
			DictionaryDO dictionary = dictionaryService.get(id);
        model.addAttribute("dictionary", dictionary);
        return prefix+"/view";
    }
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:dictionary:add")
	public R save( DictionaryDO dictionary){
		dictionary.setId(IdUtil.getStrId());
		dictionary.setCreateTime(new Date());
		dictionary.setCreateUser(getUserId());
		dictionary.setUpdateTime(new Date());
		dictionary.setUpdateUser(getUserId());
		if(dictionaryService.save(dictionary)){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:dictionary:edit")
	public R update( DictionaryDO dictionary){
		dictionary.setUpdateTime(new Date());
		dictionary.setUpdateUser(getUserId());
		dictionaryService.updateById(dictionary);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:dictionary:remove")
	public R remove( String id){

		DictionaryDO data=dictionaryService.getById(id);
        data.setDelFlag(Constants.DELETE_FLAG);
        if(dictionaryService.updateById(data)){
            return R.ok();
        }
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:dictionary:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		DictionaryDO data=new DictionaryDO();
        data.setDelFlag(Constants.DELETE_FLAG);
		dictionaryService.update(data,new LambdaQueryWrapper<DictionaryDO>().in(DictionaryDO::getId,ids));
		return R.ok();
	}
	
}
