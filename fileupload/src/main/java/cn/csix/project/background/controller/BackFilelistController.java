package cn.csix.project.background.controller;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import cn.csix.framework.aspectj.lang.annotation.Log;
import cn.csix.framework.aspectj.lang.enums.BusinessType;
import cn.csix.project.background.domain.BackFilelist;
import cn.csix.project.background.service.IBackFilelistService;
import cn.csix.framework.web.controller.BaseController;
import cn.csix.framework.web.domain.AjaxResult;
import cn.csix.common.utils.poi.ExcelUtil;
import cn.csix.framework.web.page.TableDataInfo;

/**
 * 已上传文件列表Controller
 * 
 * @author csixframework
 * @date 2020-07-28
 */
@Api("已上传文件列表")
@RestController
@RequestMapping("/background/filelist")
public class BackFilelistController extends BaseController
{
    @Autowired
    private IBackFilelistService backFilelistService;

    /**
     * 查询已上传文件列表列表
     */
    @ApiOperation("查询已上传文件列表列表")
    @PreAuthorize("@ss.hasPermi('background:filelist:list')")
    @GetMapping("/list")
    public TableDataInfo list(BackFilelist backFilelist)
    {
        startPage();
        List<BackFilelist> list = backFilelistService.selectBackFilelistList(backFilelist);
        return getDataTable(list);
    }

    /**
     * 导出已上传文件列表列表
     */
    @ApiOperation("导出已上传文件列表列表")
    @PreAuthorize("@ss.hasPermi('background:filelist:export')")
    @Log(title = "已上传文件列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BackFilelist backFilelist)
    {
        List<BackFilelist> list = backFilelistService.selectBackFilelistList(backFilelist);
        ExcelUtil<BackFilelist> util = new ExcelUtil<BackFilelist>(BackFilelist.class);
        return util.exportExcel(list, "filelist");
    }

    /**
     * 获取已上传文件列表详细信息
     */
    @ApiOperation("获取已上传文件列表详细信息")
    @PreAuthorize("@ss.hasPermi('background:filelist:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(backFilelistService.selectBackFilelistById(id));
    }

    /**
     * 新增已上传文件列表
     */
    @ApiOperation("新增已上传文件列表")
    @PreAuthorize("@ss.hasPermi('background:filelist:add')")
    @Log(title = "已上传文件列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BackFilelist backFilelist)
    {
        return toAjax(backFilelistService.insertBackFilelist(backFilelist));
    }

    /**
     * 修改已上传文件列表
     */
    @ApiOperation("修改已上传文件列表")
    @PreAuthorize("@ss.hasPermi('background:filelist:edit')")
    @Log(title = "已上传文件列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BackFilelist backFilelist)
    {
        return toAjax(backFilelistService.updateBackFilelist(backFilelist));
    }

    /**
     * 删除已上传文件列表
     */
    @ApiOperation("删除已上传文件列表")
    @PreAuthorize("@ss.hasPermi('background:filelist:remove')")
    @Log(title = "已上传文件列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(backFilelistService.deleteBackFilelistByIds(ids));
    }
}
