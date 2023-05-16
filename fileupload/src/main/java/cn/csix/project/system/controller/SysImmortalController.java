package cn.csix.project.system.controller;

import java.util.List;

import cn.csix.common.utils.poi.ExcelUtil;
import cn.csix.framework.aspectj.lang.annotation.Log;
import cn.csix.framework.aspectj.lang.enums.BusinessType;
import cn.csix.framework.web.controller.BaseController;
import cn.csix.framework.web.domain.AjaxResult;
import cn.csix.framework.web.page.TableDataInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.csix.project.system.domain.SysImmortal;
import cn.csix.project.system.service.ISysImmortalService;

/**
 * 测试信息Controller
 * 
 * @author csixframework
 * @date 2020-01-17
 */
@RestController
@RequestMapping("/system/immortal")
public class SysImmortalController extends BaseController
{
    @Autowired
    private ISysImmortalService sysImmortalService;

    /**
     * 查询测试信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:immortal:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysImmortal sysImmortal)
    {
        startPage();
        List<SysImmortal> list = sysImmortalService.selectSysImmortalList(sysImmortal);
        return getDataTable(list);
    }

    /**
     * 导出测试信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:immortal:export')")
    @Log(title = "测试信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysImmortal sysImmortal)
    {
        List<SysImmortal> list = sysImmortalService.selectSysImmortalList(sysImmortal);
        ExcelUtil<SysImmortal> util = new ExcelUtil<SysImmortal>(SysImmortal.class);
        return util.exportExcel(list, "immortal");
    }

    /**
     * 获取测试信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:immortal:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysImmortalService.selectSysImmortalById(id));
    }

    /**
     * 新增测试信息
     */
    @PreAuthorize("@ss.hasPermi('system:immortal:add')")
    @Log(title = "测试信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysImmortal sysImmortal)
    {
        return toAjax(sysImmortalService.insertSysImmortal(sysImmortal));
    }

    /**
     * 修改测试信息
     */
    @PreAuthorize("@ss.hasPermi('system:immortal:edit')")
    @Log(title = "测试信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysImmortal sysImmortal)
    {
        return toAjax(sysImmortalService.updateSysImmortal(sysImmortal));
    }

    /**
     * 删除测试信息
     */
    @PreAuthorize("@ss.hasPermi('system:immortal:remove')")
    @Log(title = "测试信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysImmortalService.deleteSysImmortalByIds(ids));
    }
}
