package cn.csix.project.system.mapper;

import cn.csix.project.system.domain.SysImmortal;

import java.util.List;

/**
 * 测试信息Mapper接口
 * 
 * @author csixframework
 * @date 2020-01-17
 */
public interface SysImmortalMapper 
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
     * 删除测试信息
     * 
     * @param id 测试信息ID
     * @return 结果
     */
    public int deleteSysImmortalById(String id);

    /**
     * 批量删除测试信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysImmortalByIds(String[] ids);
}
