package pitalo.domain.Staff;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pitalo.domain.Address;
import pitalo.domain.Person;
import pitalo.domain.Sex;

import javax.persistence.Entity;
import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
public class Doctor extends Person {

    private String salary;
    private Specialty specialty;

    @Builder
    public Doctor(Long id, String firstName, String lastName, String middleName, Sex sex, LocalDateTime registrationDate, Address address, String salary, Specialty specialty) {
        super(id, firstName, lastName, middleName, sex, registrationDate, address);
        this.salary = salary;
        this.specialty = specialty;
    }
}
