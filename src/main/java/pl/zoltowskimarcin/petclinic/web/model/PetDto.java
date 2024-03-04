package pl.zoltowskimarcin.petclinic.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PetDto {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;

    private ClientDto clientDto;
    private List<AppointmentDto> appointmentDtos;

    PetDto() {
    }

    private PetDto(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDateOfBirth(builder.dateOfBirth);
        setGender(builder.gender);
        setClientDto(builder.clientDto);
        setAppointmentDtos(builder.appointmentDtos);
    }


    public static final class Builder {
        private Long id;
        private String name;
        private LocalDate dateOfBirth;
        private Gender gender;
        private ClientDto clientDto;
        private List<AppointmentDto> appointmentDtos;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
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

        public Builder clientDto(ClientDto val) {
            clientDto = val;
            return this;
        }

        public Builder appointmentDtos(List<AppointmentDto> val) {
            appointmentDtos = val;
            return this;
        }

        public PetDto build() {
            return new PetDto(this);
        }
    }
}
