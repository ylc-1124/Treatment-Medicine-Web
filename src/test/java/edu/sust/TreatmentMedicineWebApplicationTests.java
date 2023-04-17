package edu.sust;

import edu.sust.patient.entity.Patient;
import edu.sust.patient.mapper.PatientMapper;
import edu.sust.sys.entity.User;
import edu.sust.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class TreatmentMedicineWebApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    PatientMapper patientMapper;

    @Test
    void testMapper() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void testPatientMapper() {
        List<Patient> patients = patientMapper.selectList(null);
        System.out.println(patients);
    }

}
