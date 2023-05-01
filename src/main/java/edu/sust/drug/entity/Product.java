package edu.sust.drug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 产品
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@TableName("x_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer manuId;

    private Integer drugId;

    private String name;

    private String approvalNumber;

    private String specs;

    private String photo;

    private BigDecimal price;

    private Integer remain;

    private String dosageForm;

    private String method;

    private Integer periodValidity;

    private Integer pcId;

    @TableField(exist = false)
    private Manufacturer manufacturer; //生产厂家

    @TableField(exist = false)
    private Drug drug; //对应的药品

}
