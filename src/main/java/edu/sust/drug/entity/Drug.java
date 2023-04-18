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
 * 药品
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@TableName("x_drug")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drug implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String genericName;

    private Integer classification;  //0-非处方药 1-处方药

    private String function;

    private String adrs;

    private String taboo;

    private String medicationPrecautions;

    private String storageConditions;

    private Integer deleted = 0;

}
