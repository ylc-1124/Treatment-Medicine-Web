package edu.sust.drug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 药品生产商
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@TableName("x_manufacturer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String webAddress;

    private Integer deleted = 0;

}
