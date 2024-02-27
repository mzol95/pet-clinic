package pl.zoltowskimarcin.petclinic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PetDto {

    private String name;
    private LocalDateTime dateOfBirth;
    private Gender gender;

    private ClientDto clientDto;
    private List<AppointmentDto> appointmentDtos;




}
