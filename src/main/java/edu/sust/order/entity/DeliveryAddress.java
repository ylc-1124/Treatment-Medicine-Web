package edu.sust.order.entity;

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
 * @since 2023-04-29
 */
@TableName("x_delivery_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer patId;

    private String sheng;

    private String shi;

    private String qu;

    private String jiedao;

    private String xxdz;

    private String phone;

    private String shrxm;

    private String yzbm;

    private Integer deleted;

    private Integer selected;

}
