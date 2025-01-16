package com.pc.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pc.common.utils.IdUtil;
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

import com.pc.biz.domain.ShiduDO;
import com.pc.biz.service.ShiduService;
import com.pc.common.utils.PageUtils;
import com.pc.common.utils.R;

/**
 * 湿度记录
 * 
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
 
@Controller
@RequestMapping("/biz/shidu")
public class ShiduController extends BaseController {
	@Autowired
	private ShiduService shiduService;
    private String prefix = "biz/shidu";
	@GetMapping()
	@RequiresPermissions("biz:shidu:shidu")
	String Shidu(){
	    return prefix+"/shidu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:shidu:shidu")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        startPage();
        List<ShiduDO> list =  shiduService.list(params);
        PageUtils dataTable = getDataTable(list);
        return dataTable;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:shidu:add")
	String add(){
	    return prefix+"/add";
	}

	@GetMapping("/edit/{id}")
    @RequiresPermissions("biz:shidu:edit")
    String edit(@PathVariable("id") String id,Model model){
			ShiduDO shidu = shiduService.get(id);
        model.addAttribute("shidu", shidu);
        return prefix+"/edit";
    }
    @GetMapping("/view/{id}")
    @RequiresPermissions("biz:shidu:shidu")
    String view(@PathVariable("id") String id,Model model){
			ShiduDO shidu = shiduService.get(id);
        model.addAttribute("shidu", shidu);
        return prefix+"/view";
    }
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:shidu:add")
	public R save( ShiduDO shidu){
		shidu.setId(IdUtil.getStrId());
		shidu.setCreateTime(new Date());
		if(shiduService.save(shidu)){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:shidu:edit")
	public R update( ShiduDO shidu){
		shiduService.updateById(shidu);
		return R.ok();
	}

	
}
