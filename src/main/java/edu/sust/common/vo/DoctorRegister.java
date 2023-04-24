package edu.sust.common.vo;

import edu.sust.doctor.entity.Doctor;
import edu.sust.sys.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 医生注册前端传来的对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegister {
    private User userInfo;
    private Doctor doctorInfo;
}
