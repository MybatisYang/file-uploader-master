package cn.csix.project.system.service.impl;

import java.util.List;
import cn.csix.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.csix.project.system.mapper.SysImmortalMapper;
import cn.csix.project.system.domain.SysImmortal;
import cn.csix.project.system.service.ISysImmortalService;

/**
 * 测试信息Service业务层处理
 * 
 * @author csixframework
 * @date 2020-01-17
 */
@Service
public class SysImmortalServiceImpl implements ISysImmortalService 
{
    @Autowired
    private SysImmortalMapper sysImmortalMapper;

    /**
     * 查询测试信息
     * 
     * @param id 测试信息ID
     * @return 测试信息
     */
    @Override
    public SysImmortal selectSysImmortalById(String id)
    {
        return sysImmortalMapper.selectSysImmortalById(id);
    }

    /**
     * 查询测试信息列表
     * 
     * @param sysImmortal 测试信息
     * @return 测试信息
     */
    @Override
    public List<SysImmortal> selectSysImmortalList(SysImmortal sysImmortal)
    {
        return sysImmortalMapper.selectSysImmortalList(sysImmortal);
    }

    /**
     * 新增测试信息
     * 
     * @param sysImmortal 测试信息
     * @return 结果
     */
    @Override
    public int insertSysImmortal(SysImmortal sysImmortal)
    {
        sysImmortal.setCreateTime(DateUtils.getNowDate());
        return sysImmortalMapper.insertSysImmortal(sysImmortal);
    }

    /**
     * 修改测试信息
     * 
     * @param sysImmortal 测试信息
     * @return 结果
     */
    @Override
    public int updateSysImmortal(SysImmortal sysImmortal)
    {
        sysImmortal.setUpdateTime(DateUtils.getNowDate());
        return sysImmortalMapper.updateSysImmortal(sysImmortal);
    }

    /**
     * 批量删除测试信息
     * 
     * @param ids 需要删除的测试信息ID
     * @return 结果
     */
    @Override
    public int deleteSysImmortalByIds(String[] ids)
    {
        return sysImmortalMapper.deleteSysImmortalByIds(ids);
    }

    /**
     * 删除测试信息信息
     * 
     * @param id 测试信息ID
     * @return 结果
     */
    @Override
    public int deleteSysImmortalById(String id)
    {
        return sysImmortalMapper.deleteSysImmortalById(id);
    }
}
