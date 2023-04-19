package edu.sust.doctor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@TableName("x_hospital")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String hospName;

    private String phone;

    private String address;

    private String introduction;

    private Integer deleted = 0;
}
