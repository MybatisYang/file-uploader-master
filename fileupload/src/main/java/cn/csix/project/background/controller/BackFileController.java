package cn.csix.project.background.controller;

import cn.csix.common.constant.CommonConstant;
import cn.csix.framework.aspectj.lang.annotation.Log;
import cn.csix.framework.aspectj.lang.enums.BusinessType;
import cn.csix.framework.web.controller.BaseController;
import cn.csix.framework.web.domain.AjaxResult;
import cn.csix.project.background.domain.BackChunk;
import cn.csix.project.background.domain.BackFilelist;
import cn.csix.project.background.domain.vo.CheckChunkVO;
import cn.csix.project.background.service.IBackFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 用户报告申请记录Controller
 * 
 * @author csixframework
 * @date 2020-07-20
 */
@Api("文件管理")
@RestController
@RequestMapping("/background/file")
public class BackFileController extends BaseController
{
    @Autowired
    private IBackFileService backFileService;

    /**
     * 查询用户报告申请记录列表
     */
//    @ApiOperation("查询用户报告申请记录列表")
//    @PreAuthorize("@ss.hasPermi('background:report:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(BackFileVO backUserReportVO)
//    {
//        startPage();
//        List<BackFileVO> list = backFileService.selectBackFileList(backUserReportVO);
//        return getDataTable(list);
//    }

    /**
     * 上传文件
     */
    @ApiOperation("上传文件")
    @PreAuthorize("@ss.hasPermi('background:file:upload')")
    @PostMapping("/upload")
    public AjaxResult postFileUpload(@ModelAttribute BackChunk chunk, HttpServletResponse response)
    {
        int i = backFileService.postFileUpload(chunk, response);
        return toAjax(i);
    }

    /**
     * 检查文件上传状态
     */
    @ApiOperation("检查文件上传状态")
    @PreAuthorize("@ss.hasPermi('background:file:check')")
    @GetMapping("/upload")
    public CheckChunkVO getFileUpload(@ModelAttribute BackChunk chunk, HttpServletResponse response)
    {
        //查询根据md5查询文件是否存在
        CheckChunkVO fileUpload = backFileService.getFileUpload(chunk, response);
        return fileUpload;
    }

    /**
     * 删除用户报告申请记录
     */
    @ApiOperation("删除文件")
    @PreAuthorize("@ss.hasPermi('background:file:remove')")
    @Log(title = "用户删除文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(backFileService.deleteBackFileByIds(id));
    }

    /**
     * 检查文件上传状态
     */
    @ApiOperation("合并文件")
    @PreAuthorize("@ss.hasPermi('background:file:merge')")
    @PostMapping("/merge")
    public AjaxResult merge(BackFilelist fileInfo)
    {
        int i = backFileService.mergeFile(fileInfo);
        if(i == CommonConstant.UPDATE_EXISTS.intValue()){
            //应对合并时断线导致的无法重新申请合并的问题
            return new AjaxResult(200,"已合并,无需再次提交");
        }
        return toAjax(i);
    }
}
