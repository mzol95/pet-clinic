package pl.zoltowskimarcin.petclinic.web.model.pet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class BasicPetDto {

    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;

    BasicPetDto() {
    }

    public BasicPetDto(String name, LocalDate dateOfBirth, Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
