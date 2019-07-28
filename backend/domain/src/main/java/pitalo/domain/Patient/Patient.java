package pitalo.domain.Patient;

import lombok.*;
import pitalo.domain.Address;
import pitalo.domain.Person;
import pitalo.domain.Sex;
import pitalo.domain.Visitation.Visitation;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient extends Person {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "history_id")
    private MedicalHistory medicalHistory;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;

    @Singular("emergencyContact")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", orphanRemoval = true)
    private List<EmergencyContact> emergencyContacts;

    @Singular("visitation")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", orphanRemoval = true)
    private List<Visitation> visitations;

    @NotEmpty(message = "Occupation is required")
    private String occupation;

    @NotEmpty(message = "Health number is required")
    @Pattern(regexp = "\\d{4}[\\-]?\\d{3}[\\-]?\\d{3}[\\-]?[A-Z]{2}", message = "Health number must match format: 1234-123-123-AB")
    private String healthNumber;

    @Builder
    public Patient(Long id, String firstName, String lastName, String middleName, String email, @NotNull Sex sex, LocalDateTime registrationDate, Address address, @NotEmpty(message = "Phone number is required") @Pattern(regexp = "(\\d{3})[\\-]?\\d{3}[\\-]?\\d{4}", message = "Phone number must match format: (123)-123-1234") String phoneNumber, MedicalHistory medicalHistory, Insurance insurance, List<EmergencyContact> emergencyContacts, List<Visitation> visitations, @NotEmpty(message = "Occupation is required") String occupation, @NotEmpty(message = "Health number is required") @Pattern(regexp = "\\d{4}[\\-]?\\d{3}[\\-]?\\d{3}[\\-]?[A-Z]{2}", message = "Health number must match format: 1234-123-123-AB") String healthNumber) {
        super(id, firstName, lastName, middleName, email, sex, registrationDate, address, phoneNumber);
        this.medicalHistory = medicalHistory;
        this.insurance = insurance;
        this.emergencyContacts = emergencyContacts;
        this.visitations = visitations;
        this.occupation = occupation;
        this.healthNumber = healthNumber;
    }
}
