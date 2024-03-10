package pl.zoltowskimarcin.petclinic.web.model.appointment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@EqualsAndHashCode
public class BasicAppointmentDto {

    private LocalDateTime appointmentDate;
    private boolean finished;


    BasicAppointmentDto() {
    }

    private BasicAppointmentDto(Builder builder) {
        setAppointmentDate(builder.appointmentDate);
        setFinished(builder.finished);
    }


    public static final class Builder {
        private LocalDateTime appointmentDate;
        private boolean finished;

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

        public BasicAppointmentDto build() {
            return new BasicAppointmentDto(this);
        }
    }
}
