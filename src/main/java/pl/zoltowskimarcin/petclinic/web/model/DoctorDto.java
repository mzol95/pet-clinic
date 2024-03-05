package pl.zoltowskimarcin.petclinic.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class DoctorDto {

    private String name;
    private String surname;
    private List<AppointmentDto> appointmentDtos;

    private DoctorDto() {
    }

    private DoctorDto(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        appointmentDtos = builder.appointmentDtos;
    }


    public static final class Builder {
        private String name;
        private String surname;
        private List<AppointmentDto> appointmentDtos;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }

        public Builder appointmentDtos(List<AppointmentDto> val) {
            appointmentDtos = val;
            return this;
        }

        public DoctorDto build() {
            return new DoctorDto(this);
        }
    }
}

