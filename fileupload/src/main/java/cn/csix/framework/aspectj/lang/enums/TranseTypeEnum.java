package cn.csix.framework.aspectj.lang.enums;

/**
 * @author: zrd
 * @Date: 2019/11/20 15:50
 * @Description:
 */
public enum TranseTypeEnum {

    // 待办事项
    DIST("DIST", "字典"),
    FOLDER("FOLDER", "文件夹"),
    DEPT("DEPT", "部门"),
    MULTIPLE_FOLDER("MULTIPLE_FOLDER", "文件夹树"),
    MULTIPLE_DIST("MULTIPLE_DIST", "多选数据字典"),
    MULTIPLE_USER("MULTIPLE_USER", "多选用户"),
    DATE("DATE", "日期"),
    USER("USER", "用户"),
    ENUM("ENUM", "枚举类"),
    ;
    private String key;
    private String desc;

    TranseTypeEnum(String i, String desc) {
        this.key = i;
        this.desc = desc;

    }

    /**
     * 描述：  通过值获取枚举
     * 备注：
     * 日期： 16:07 2019/12/13
     * 作者： zrd
     *
     * @param i
     * @return com.arr.common.enums.TranseTypeEnum
     **/
    public static TranseTypeEnum getEnumByKey(String i) {
        for (TranseTypeEnum ele : values()) {
            if (ele.getKey().equalsIgnoreCase(i)) {
                return ele;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }
}
