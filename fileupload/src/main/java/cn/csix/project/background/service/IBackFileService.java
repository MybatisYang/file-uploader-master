package cn.csix.project.background.service;

import cn.csix.framework.web.domain.AjaxResult;
import cn.csix.project.background.domain.BackChunk;
import cn.csix.project.background.domain.BackFilelist;
import cn.csix.project.background.domain.vo.CheckChunkVO;

import javax.servlet.http.HttpServletResponse;

public interface IBackFileService {

    int postFileUpload(BackChunk chunk, HttpServletResponse response);

    CheckChunkVO getFileUpload(BackChunk chunk, HttpServletResponse response);

    int deleteBackFileByIds(Long id);

    int mergeFile(BackFilelist fileInfo);
}
