package pl.zoltowskimarcin.petclinic.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@EqualsAndHashCode
public class AppointmentDto {

    private LocalDateTime appointmentDate;
    private boolean finished;

    private PetDto petDto;
    private DoctorDto doctorDto;

    private AppointmentDto(Builder builder) {
        setAppointmentDate(builder.appointmentDate);
        setFinished(builder.finished);
        setPetDto(builder.petDto);
        setDoctorDto(builder.doctorDto);
    }

    AppointmentDto() {
    }


    public static final class Builder {
        private LocalDateTime appointmentDate;
        private boolean finished;
        private PetDto petDto;
        private DoctorDto doctorDto;

        public Builder() {
        }

        public Builder appointmentDate(LocalDateTime val) {
            appointmentDate = val;
            return this;
        }

        public Builder finished(boolean val) {
            finished = val;
            return this;
        }

        public Builder petDto(PetDto val) {
            petDto = val;
            return this;
        }

        public Builder doctorDto(DoctorDto val) {
            doctorDto = val;
            return this;
        }

        public AppointmentDto build() {
            return new AppointmentDto(this);
        }
    }
}
