package edu.sust.drug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-04-19
 */
@TableName("x_prescription_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer presId;

    private Integer prodId;

    @TableField(exist = false)
    private String prodName;  //产品名称

    @TableField(exist = false)
    private String dosageForm; //产品剂型

    @TableField(exist = false)
    private String approvalNumber; //国药准字

    private String method;

}
