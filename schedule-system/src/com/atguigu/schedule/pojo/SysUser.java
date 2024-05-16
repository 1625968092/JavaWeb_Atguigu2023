package com.atguigu.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 1. 实体类的类名和表格名应该对应
 * 2. 实体类的属性名和表格的列名应该对应
 * 3. 每个属性都必须是私有的
 * 4. 每个属性都应该具有getter和setter
 * 5. 必须具备无参构造器
 * 6. 应该实现序列化接口（缓存、分布式项目数据传递 可能会将对象序列化）
 * 7. 应该重写类的hashCode和equals方法
 * 8. toString 是否重写都可以
 *
 */

/**
 * 使用lombok生成getter、setter、equals等等....
 *
 * 1. 检查idea是否已经安装了lombok插件
 * 2. 检查是否勾选了enable annotation processor
 * 3. 在实体类上加入注解
 */

@AllArgsConstructor  //添加了全参构造
@NoArgsConstructor   //添加了无参构造器
@Data                  //getter、setter、equals等等....
public class SysUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;
}
