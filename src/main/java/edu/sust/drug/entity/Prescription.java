package edu.sust.drug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ylc
 * @since 2023-04-19
 */
@TableName("x_prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(exist = false)
    private Integer consultId;  //问诊记录的ID

    private Integer patId;

    private Integer docId;

    @TableField(exist = false)
    private List<Product> productList;

    @TableField(exist = false)
    private String docName;

    private Integer fyrId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fyDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date kjDate;

    private Integer status;

    private String lczd;

    private String bzxx;

    private Integer deleted = 0;

}
