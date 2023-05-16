package cn.csix.project.system.service;

import cn.csix.project.system.domain.SysImmortal;

import java.util.List;

/**
 * 测试信息Service接口
 * 
 * @author csixframework
 * @date 2020-01-17
 */
public interface ISysImmortalService 
{
    /**
     * 查询测试信息
     * 
     * @param id 测试信息ID
     * @return 测试信息
     */
    public SysImmortal selectSysImmortalById(String id);

    /**
     * 查询测试信息列表
     * 
     * @param sysImmortal 测试信息
     * @return 测试信息集合
     */
    public List<SysImmortal> selectSysImmortalList(SysImmortal sysImmortal);

    /**
     * 新增测试信息
     * 
     * @param sysImmortal 测试信息
     * @return 结果
     */
    public int insertSysImmortal(SysImmortal sysImmortal);

    /**
     * 修改测试信息
     * 
     * @param sysImmortal 测试信息
     * @return 结果
     */
    public int updateSysImmortal(SysImmortal sysImmortal);

    /**
     * 批量删除测试信息
     * 
     * @param ids 需要删除的测试信息ID
     * @return 结果
     */
    public int deleteSysImmortalByIds(String[] ids);

    /**
     * 删除测试信息信息
     * 
     * @param id 测试信息ID
     * @return 结果
     */
    public int deleteSysImmortalById(String id);
}
