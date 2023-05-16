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
import cn.csix.project.background.domain.BackChunk;
import cn.csix.project.background.service.IBackChunkService;
import cn.csix.framework.web.controller.BaseController;
import cn.csix.framework.web.domain.AjaxResult;
import cn.csix.common.utils.poi.ExcelUtil;
import cn.csix.framework.web.page.TableDataInfo;

/**
 * 文件分片管理Controller
 * 
 * @author csixframework
 * @date 2020-07-28
 */
@Api("文件分片管理")
@RestController
@RequestMapping("/background/chunk")
public class BackChunkController extends BaseController
{
    @Autowired
    private IBackChunkService backChunkService;

    /**
     * 查询文件分片管理列表
     */
    @ApiOperation("查询文件分片管理列表")
    @PreAuthorize("@ss.hasPermi('background:chunk:list')")
    @GetMapping("/list")
    public TableDataInfo list(BackChunk backChunk)
    {
        startPage();
        List<BackChunk> list = backChunkService.selectBackChunkList(backChunk);
        return getDataTable(list);
    }

    /**
     * 导出文件分片管理列表
     */
    @ApiOperation("导出文件分片管理列表")
    @PreAuthorize("@ss.hasPermi('background:chunk:export')")
    @Log(title = "文件分片管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BackChunk backChunk)
    {
        List<BackChunk> list = backChunkService.selectBackChunkList(backChunk);
        ExcelUtil<BackChunk> util = new ExcelUtil<BackChunk>(BackChunk.class);
        return util.exportExcel(list, "chunk");
    }

    /**
     * 获取文件分片管理详细信息
     */
    @ApiOperation("获取文件分片管理详细信息")
    @PreAuthorize("@ss.hasPermi('background:chunk:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(backChunkService.selectBackChunkById(id));
    }

    /**
     * 新增文件分片管理
     */
    @ApiOperation("新增文件分片管理")
    @PreAuthorize("@ss.hasPermi('background:chunk:add')")
    @Log(title = "文件分片管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BackChunk backChunk)
    {
        return toAjax(backChunkService.insertBackChunk(backChunk));
    }

    /**
     * 修改文件分片管理
     */
    @ApiOperation("修改文件分片管理")
    @PreAuthorize("@ss.hasPermi('background:chunk:edit')")
    @Log(title = "文件分片管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BackChunk backChunk)
    {
        return toAjax(backChunkService.updateBackChunk(backChunk));
    }

    /**
     * 删除文件分片管理
     */
    @ApiOperation("删除文件分片管理")
    @PreAuthorize("@ss.hasPermi('background:chunk:remove')")
    @Log(title = "文件分片管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(backChunkService.deleteBackChunkByIds(ids));
    }
}
