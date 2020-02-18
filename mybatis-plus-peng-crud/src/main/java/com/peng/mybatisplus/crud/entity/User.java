package com.peng.mybatisplus.crud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author peng
 * @since 2020-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名(校验：不能为空，不能超过20个字符串）)
     */
    @NotNull(message = "姓名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符")
    private String name;

    /**
     * 年龄(校验：不能为空，18到60）)
     */
    @NotNull(message = "年龄不能为空")
    @Max(value = 60, message = "不能大于60岁")
    @Min(value = 18, message = "不能小于18岁")
    private Integer age;

    /**
     * 邮箱（校验格式）
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮件格式不正确")
    private String email;

}
