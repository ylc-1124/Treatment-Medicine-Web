package edu.sust.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.sust.drug.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author ylc
 * @since 2023-04-29
 */
@TableName("x_order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private Integer prodId;

    @TableField(exist = false)
    private Product prod;

    private BigDecimal price;

    private Integer num;

    private BigDecimal sum;

    private Integer deleted;
}
