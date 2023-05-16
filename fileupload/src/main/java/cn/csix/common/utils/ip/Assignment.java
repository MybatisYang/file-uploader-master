package cn.csix.common.utils.ip;

/**
 * @Author: xjd
 * @Date: 2020/7/8 11:37
 * @Description:
 **/
class Tank {
    int level;
}
public class Assignment {
    public static void main(String[] args){
        Tank tank1 = new Tank();
        Tank tank2 = new Tank();
        tank1.level = 9;
        tank2.level = 47;
        System.out.println("tank1:"+tank1.level + "tank2:" + tank2.level);
        tank1 = tank2;
        System.out.println("tank1:"+tank1.level + "tank2:" + tank2.level);
        tank1.level = 27;
        System.out.println("tank1:"+tank1.level + "tank2:" + tank2.level);
    }
    /*
    tank1:9tank2:47
    tank1:47tank2:47
    tank1:27tank2:27
     */
}
