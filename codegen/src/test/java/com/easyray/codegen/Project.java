package com.easyray.codegen;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Date: 20-2-5
 * @Author: wyy
 */
@TableName("project")
public class Project {
    @TableId
    private Long id;
    @TableField()
    private String name;
}
