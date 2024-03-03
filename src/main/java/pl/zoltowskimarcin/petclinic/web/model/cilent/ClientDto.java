package pl.zoltowskimarcin.petclinic.web.model.cilent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.web.model.AppointmentDto;
import pl.zoltowskimarcin.petclinic.web.model.PetDto;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ClientDto {

    private String name;
    private String surname;
    private String street;
    private String city;
    private String postalCode;
    private String phone;
    private List<PetDto> petDtos;
    private List<AppointmentDto> appointmentDtos;

    private ClientDto() {
    }

    private ClientDto(Builder builder) {
        setName(builder.name);
        setSurname(builder.surname);
        setStreet(builder.street);
        setCity(builder.city);
        setPostalCode(builder.postalCode);
        setPhone(builder.phone);
        setPetDtos(builder.petDtos);
        setAppointmentDtos(builder.appointmentDtos);
    }


    public static final class Builder {
        private String name;
        private String surname;
        private String street;
        private String city;
        private String postalCode;
        private String phone;
        private List<PetDto> petDtos;
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

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder postalCode(String val) {
            postalCode = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder petDtos(List<PetDto> val) {
            petDtos = val;
            return this;
        }

        public Builder appointmentDtos(List<AppointmentDto> val) {
            appointmentDtos = val;
            return this;
        }

        public ClientDto build() {
            return new ClientDto(this);
        }
    }


}
