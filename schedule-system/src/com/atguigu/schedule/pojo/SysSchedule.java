package com.atguigu.schedule.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor  //添加了全参构造
@NoArgsConstructor   //添加了无参构造器
@Data                  //getter、setter、equals等等....
public class SysSchedule implements Serializable {
        private Integer sid;
        private Integer uid;
        private String title;
        private Integer completed;

}
