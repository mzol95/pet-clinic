package pl.zoltowskimarcin.petclinic.mapper;

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

    public <T> T mapToDto(Appointment appointment, Class<T> type) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping Appointment: " + appointment + "to AppointmentDto");
        T mappedAppointment = modelMapper.map(appointment, type);
        log.info("Mapped Appointment: " + appointment + "to AppointmentDto: " + mappedAppointment);
        return mappedAppointment;
    }

    public Appointment mapToEntity(AppointmentDto appointmentDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(AppointmentDto.class, Appointment.class).addMappings(mapper -> mapper.skip(Appointment::setId));
        log.info("Mapping AppointmentDto: " + appointmentDto + "to Appointment");
        Appointment mappedAppointment = modelMapper.map(appointmentDto, Appointment.class);
        log.info("Mapped AppointmentDto: " + appointmentDto + "to Appointment: " + mappedAppointment);
        return mappedAppointment;
    }

    public <T> List<T> mapToDtoList(List<Appointment> appointments, Class<T> type) {
        log.info("Mapping List<Appointment> to List<AppointmentDto>");
        List<T> appointmentDtos = appointments.stream()
                .map(src -> mapToDto(src, type))
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
