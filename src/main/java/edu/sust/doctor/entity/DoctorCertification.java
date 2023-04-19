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
@TableName("x_doctor_certification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorCertification implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer docId;

    private Integer status;

    private String url;

    private Integer deleted = 0;

}
