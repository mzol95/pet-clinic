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

    private BasicPetDto(Builder builder) {
        setName(builder.name);
        setDateOfBirth(builder.dateOfBirth);
        setGender(builder.gender);
    }

    public static final class Builder {
        private String name;
        private LocalDate dateOfBirth;
        private Gender gender;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder dateOfBirth(LocalDate val) {
            dateOfBirth = val;
            return this;
        }

        public Builder gender(Gender val) {
            gender = val;
            return this;
        }

        public BasicPetDto build() {
            return new BasicPetDto(this);
        }
    }
}
