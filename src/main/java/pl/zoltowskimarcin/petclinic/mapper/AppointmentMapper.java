package pl.zoltowskimarcin.appointmentclinic.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AppointmentMapper {

    public AppointmentDto mapToDto(Appointment appointment) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping Appointment: " + appointment + "to AppointmentDto");
        AppointmentDto mappedAppointment = modelMapper.map(appointment, AppointmentDto.class);
        log.info("Mapped Appointment: " + appointment + "to AppointmentDto: " + mappedAppointment);
        return mappedAppointment;
    }

    public Appointment mapToEntity(AppointmentDto appointmentDto) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping AppointmentDto: " + appointmentDto + "to Appointment");
        Appointment mappedAppointment = modelMapper.map(appointmentDto, Appointment.class);
        log.info("Mapped AppointmentDto: " + appointmentDto + "to Appointment: " + mappedAppointment);
        return mappedAppointment;
    }

    public List<AppointmentDto> mapToDtoList(List<Appointment> appointments) {
        log.info("Mapping List<Appointment> to List<AppointmentDto>");
        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return appointmentDtos;
    }

    public List<Appointment> mapToEntityList(List<AppointmentDto> appointmentDtos) {
        log.info("Mapping List<AppointmentDto> to List<Appointment>");
        List<Appointment> appointments = appointmentDtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        return appointments;
    }

}
