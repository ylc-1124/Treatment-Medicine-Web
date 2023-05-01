package edu.sust.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ylc
 * @since 2023-04-29
 */
@TableName("x_patient_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer patId;

    private Integer presId;

    private String orderNo;

    private String subject; //订单名称

    private String alipayNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date payDate;

    private Integer addressId;

    @TableField(exist = false)
    private DeliveryAddress address;

    private Integer status;

    private BigDecimal totalAmount;

    private Integer deleted;
}
