package pl.zoltowskimarcin.petclinic.web.model;

import lombok.Data;

import java.util.List;

@Data

public class DoctorDto {

    private String name;
    private String surname;

    private List<AppointmentDto> appointmentDtos;
    private WorkScheduleDto workScheduleDto;

    public DoctorDto() {
    }

    public DoctorDto(String name, String surname, List<AppointmentDto> appointmentDtos, WorkScheduleDto workScheduleDto) {
        this.name = name;
        this.surname = surname;
        this.appointmentDtos = appointmentDtos;
        this.workScheduleDto = workScheduleDto;
    }
}
