package cn.csix.project.background.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.csix.framework.aspectj.lang.annotation.Excel;
import cn.csix.framework.web.domain.BaseEntity;

/**
 * 已上传文件列表对象 sys_filelist
 * 
 * @author csixframework
 * @date 2020-07-28
 */
@ApiModel("已上传文件列表")
public class BackFilelist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 文件名 */
    @Excel(name = "文件名")
    @ApiModelProperty("文件名")
    private String filename;

    /** 唯一标识,MD5 */
    @Excel(name = "唯一标识,MD5")
    @ApiModelProperty("唯一标识,MD5")
    private String identifier;

    /** 链接 */
    @Excel(name = "链接")
    @ApiModelProperty("链接")
    private String url;

    /** 本地地址 */
    @Excel(name = "本地地址")
    @ApiModelProperty("本地地址")
    private String location;

    /** 文件总大小 */
    @Excel(name = "文件总大小")
    @ApiModelProperty("文件总大小")
    private Long totalSize;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setIdentifier(String identifier) 
    {
        this.identifier = identifier;
    }

    public String getIdentifier() 
    {
        return identifier;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setTotalSize(Long totalSize) 
    {
        this.totalSize = totalSize;
    }

    public Long getTotalSize() 
    {
        return totalSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("filename", getFilename())
            .append("identifier", getIdentifier())
            .append("url", getUrl())
            .append("location", getLocation())
            .append("totalSize", getTotalSize())
            .toString();
    }
}
