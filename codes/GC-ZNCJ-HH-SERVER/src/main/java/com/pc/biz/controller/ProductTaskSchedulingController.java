package com.pc.biz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pc.biz.domain.ProduceAdministrationDO;
import com.pc.biz.domain.ProduceDataDO;
import com.pc.biz.domain.ProductTaskSchedulingDO;
import com.pc.biz.dto.ProductDTO;
import com.pc.biz.dto.ProductTaskSchedulingExcelDTO;
import com.pc.biz.service.IProductTaskSchedulingService;
import com.pc.biz.service.ProduceAdministrationService;
import com.pc.biz.service.ProduceDataService;
import com.pc.common.annotation.Log;
import com.pc.common.controller.BaseController;
import com.pc.common.utils.PageUtils;
import com.pc.common.utils.R;
import com.pc.system.domain.ShebeiDO;
import com.pc.system.service.ShebeiService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务排产表
 *
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */

@Controller
@RequestMapping("/biz/productTaskScheduling")
public class ProductTaskSchedulingController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ProductTaskSchedulingController.class);

    @Autowired
    private IProductTaskSchedulingService productTaskSchedulingService;

    @Autowired
    private ShebeiService shebeiService;

    @Autowired
    private ProduceDataService produceDataService;

    @Autowired
    private ProduceAdministrationService produceAdministrationService;

    @GetMapping()
    @RequiresPermissions("biz:productTaskScheduling:list")
    public String ProductTaskScheduling(Model model) {
        List<ShebeiDO> list = shebeiService.list(new HashMap<>());
        model.addAttribute("shebeis", list);
        return "biz/productTaskScheduling/productTaskScheduling";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("biz:productTaskScheduling:list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        startPage();
        List<HashMap> hashMaps = productTaskSchedulingService.listPage(params);
        PageUtils dataTable = getDataTable(hashMaps);
        return dataTable;
    }

    @GetMapping("/add")
    @RequiresPermissions("biz:productTaskScheduling:add")
    public String add(Model model) {
        List<ShebeiDO> list = shebeiService.list(new HashMap<>());
        List<ProduceAdministrationDO> list1 = produceAdministrationService.list(new HashMap<>());
        model.addAttribute("shebeis", list);
        model.addAttribute("produceAdministrations", list1);
        return "biz/productTaskScheduling/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("biz:productTaskScheduling:edit")
    public String edit(@PathVariable("id") String id, Model model) {
        ProductTaskSchedulingDO productTaskScheduling = productTaskSchedulingService.getById(id);
        model.addAttribute("productTaskScheduling", productTaskScheduling);
        return "biz/productTaskScheduling/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("biz:productTaskScheduling:add")
    public R save(ProductTaskSchedulingDO productTaskScheduling) {
        if (productTaskSchedulingService.artificialSave(productTaskScheduling)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("biz:productTaskScheduling:edit")
    public R update(ProductTaskSchedulingDO productTaskScheduling) {
        productTaskSchedulingService.updateById(productTaskScheduling);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("biz:productTaskScheduling:remove")
    public R remove(String id) {
        /*ProductTaskSchedulingDO one = productTaskSchedulingService.getOne(
                new LambdaQueryWrapper<ProductTaskSchedulingDO>()
                        .eq(ProductTaskSchedulingDO::getId, id)
                        .ge(ProductTaskSchedulingDO::getPlanDate, DateUtil.formatDate(new Date())));
        if (one == null) {
            return R.error("数据计划日期早于当前日期无法删除");
        }*/
        ProduceDataDO one1 = produceDataService.getOne(
                new LambdaQueryWrapper<ProduceDataDO>()
                        .eq(ProduceDataDO::getJhId, id)
                        .ne(ProduceDataDO::getStatus, 0)
        );
        if (one1 != null) {
            return R.error("数据已开始生产无法删除");
        }
        productTaskSchedulingService.bizRemove(id);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("biz:productTaskScheduling:remove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        for(String id : ids){
            ProduceDataDO one1 = produceDataService.getOne(
                    new LambdaQueryWrapper<ProduceDataDO>()
                            .eq(ProduceDataDO::getJhId, id)
                            .ne(ProduceDataDO::getStatus, 0)
            );
            if (one1 != null) {
                //return R.error("数据已开始生产无法删除");
                logger.error("数据已开始生产无法删除 ---> id:"+id);
            }else {
                productTaskSchedulingService.bizRemove(id);
            }
        }
        return R.ok();
    }

    @Log("执行导入")
    @PostMapping("/importExcel")
    @RequiresPermissions("biz:productTaskScheduling:add")
    @ResponseBody
    public R importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        productTaskSchedulingService.importExcel(file);
        return R.ok();
    }

    /**
     * 数据导入
     */
    @Log("执行导出模板")
    @GetMapping("/exportTemplate")
    @RequiresPermissions("biz:productTaskScheduling:add")
    public void exportTemplate(HttpServletResponse response) throws Exception {
        String fileName = "任务排产模板" + DateUtil.date().toString(DatePattern.PURE_DATETIME_PATTERN) + ".xls";

        List<ProductTaskSchedulingExcelDTO> list = new ArrayList<>();
        ProductTaskSchedulingExcelDTO dto1 = new ProductTaskSchedulingExcelDTO();
        dto1.setPlanDate("2021-12-14");
        dto1.setTypeName("白班");
        dto1.setLevel("1");
        dto1.setDeviceCode("RSBZ32");
        dto1.setDeviceName("单套膜机");
        dto1.setCode("5201-200628001");
        dto1.setProductCode("FSAK-FR008-004A");
        dto1.setProductName("舒克 saky Pro 中国版 牙线棒 AIS");
        dto1.setProductSpec("黑色 扁棉线900D 竹炭 50PCS/圆罐");
        dto1.setProductUnit("罐");
        dto1.setOrderQuantity("31104");
        dto1.setLastOrderQuantity("");
        dto1.setPlanOrderQuantity("5000");
        dto1.setThisOrderQuantity("");
        dto1.setPlanTime("7.0");
        dto1.setPlanTotalWorkTime("35.0");
        dto1.setPlanPerson("5");
        dto1.setGoodsModel1("XX23");
        dto1.setGoodsName1("X牙线棒");
        dto1.setPlanIntoNum1("500000");
        dto1.setGoodsUnit1("个");
        dto1.setGoodsModel2("");
        dto1.setGoodsName2("");
        dto1.setPlanIntoNum2("");
        dto1.setGoodsUnit2("个");
        dto1.setRemark("");

        ProductTaskSchedulingExcelDTO dto2 = new ProductTaskSchedulingExcelDTO();
        dto2.setPlanDate("2021-12-14");
        dto2.setTypeName("夜班");
        dto2.setLevel("1");
        dto2.setDeviceCode("RSBZ33");
        dto2.setDeviceName("三、四盒套膜机");
        dto2.setCode("5108-200606005");
        dto2.setProductCode("FMNS-FR043-001D");
        dto2.setProductName("名创优品 MINISO 中国版 牙线棒 NS");
        dto2.setProductSpec("绿色 扁棉线1050D 薄荷 40PCS/袋 2袋/盒");
        dto2.setProductUnit("盒");
        dto2.setOrderQuantity("69984");
        dto2.setLastOrderQuantity("");
        dto2.setPlanOrderQuantity("3340");
        dto2.setThisOrderQuantity("");
        dto2.setPlanTime("7.0");
        dto2.setPlanTotalWorkTime("35.0");
        dto2.setPlanPerson("5");
        dto2.setGoodsModel1("");
        dto2.setGoodsName1("");
        dto2.setPlanIntoNum1("");
        dto2.setGoodsUnit1("");
        dto2.setGoodsModel2("");
        dto2.setGoodsName2("");
        dto2.setPlanIntoNum2("");
        dto2.setGoodsUnit2("");
        dto2.setRemark("");

        list.add(dto1);
        list.add(dto2);
        /**
         * 动态隐藏列
         */
        //for(SubsidyHistoryExcelDTO dto :list){
        //    EasyPoiUtil<SubsidyHistoryExcelDTO> easyPoiUtil = new EasyPoiUtil<>(dto);
        //    easyPoiUtil.hideColumn("companyName", true);
        //}
        ExportParams exportParams = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ProductTaskSchedulingExcelDTO.class, list);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 生产调度表
     * @param model
     * @return
     */
    @Log("生产调度表")
    @GetMapping("/scheduling")
    @RequiresPermissions("biz:productTaskScheduling:scheduling")
    public String scheduling(Model model) {
        List<ShebeiDO> list = shebeiService.list(new HashMap<>());
        model.addAttribute("shebeis", list);
        return "biz/productTaskScheduling/scheduling";
    }

    /**
     * 生产调度表数据
     * @param params
     * @return
     */
    @Log("生产调度表数据")
    @GetMapping("/schedulingList")
    @RequiresPermissions("biz:productTaskScheduling:scheduling")
    @ResponseBody
    public PageUtils schedulingList(@RequestParam Map<String, Object> params) {
        params.put("type", 1);
        startPage();
        List<ProductDTO> hashMaps = productTaskSchedulingService.getList(params);
        PageUtils dataTable = getDataTable(hashMaps);
        return dataTable;
    }
    @Log("生产调度表设置顺序")
    @GetMapping("/setOrder/{id}")
    @RequiresPermissions("biz:productTaskScheduling:scheduling")
    public String setOrder(@PathVariable("id") String id, Model model) {
        ProductTaskSchedulingDO productTaskScheduling = productTaskSchedulingService.getById(id);
        model.addAttribute("productTaskScheduling", productTaskScheduling);
        return "biz/productTaskScheduling/edit";
    }

    /**
     * 修改
     */
    @Log("生产调度表设置顺序")
    @ResponseBody
    @RequestMapping("/updateOrder")
    @RequiresPermissions("biz:productTaskScheduling:scheduling")
    public R updateOrder(ProductTaskSchedulingDO productTaskScheduling) {
        productTaskSchedulingService.updateById(productTaskScheduling);
        return R.ok();
    }

}
