package edu.sust.doctor.entity;

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
 * 
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@TableName("x_doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String doctorName;

    private Integer hospId;

    @TableField(exist = false)
    private String hospitalName;

    private String sex;

    private Integer age;

    private Integer departmentId;

    @TableField(exist = false)
    private String departmentName;

    private String photo;

    private String introduction;

    private BigDecimal wzPrice;

    private String speciality;

    private BigDecimal income;

    private String certification;

    private Integer deleted = 0;

    private String zc;

    private String hjry;

    private String kycg;


}
