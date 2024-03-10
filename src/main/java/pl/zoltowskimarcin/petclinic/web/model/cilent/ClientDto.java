package pl.zoltowskimarcin.petclinic.web.model.cilent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.repository.entity.Address;
import pl.zoltowskimarcin.petclinic.web.model.appointment.BasicAppointmentDto;
import pl.zoltowskimarcin.petclinic.web.model.pet.BasicPetDto;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ClientDto {

    private String name;
    private String surname;
    private String phone;

    private Address address;
    private List<BasicPetDto> petDtos;
    private List<BasicAppointmentDto> appointmentDtos;

    private ClientDto() {
    }

    private ClientDto(Builder builder) {
        setName(builder.name);
        setSurname(builder.surname);
        setPhone(builder.phone);
        setAddress(builder.address);
        setPetDtos(builder.petDtos);
        setAppointmentDtos(builder.appointmentDtos);
    }


    public static final class Builder {
        private String name;
        private String surname;
        private String phone;
        private Address address;
        private List<BasicPetDto> petDtos;
        private List<BasicAppointmentDto> appointmentDtos;

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

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder address(Address val) {
            address = val;
            return this;
        }

        public Builder petDtos(List<BasicPetDto> val) {
            petDtos = val;
            return this;
        }

        public Builder appointmentDtos(List<BasicAppointmentDto> val) {
            appointmentDtos = val;
            return this;
        }

        public ClientDto build() {
            return new ClientDto(this);
        }
    }
}
