package pl.zoltowskimarcin.doctorclinic.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DoctorMapper {

    public DoctorDto mapToDto(Doctor doctor) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping Doctor: " + doctor + "to DoctorDto");
        DoctorDto mappedDoctor = modelMapper.map(doctor, DoctorDto.class);
        log.info("Mapped Doctor: " + doctor + "to DoctorDto: " + mappedDoctor);
        return mappedDoctor;
    }

    public Doctor mapToEntity(DoctorDto doctorDto) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping DoctorDto: " + doctorDto + "to Doctor");
        Doctor mappedDoctor = modelMapper.map(doctorDto, Doctor.class);
        log.info("Mapped DoctorDto: " + doctorDto + "to Doctor: " + mappedDoctor);
        return mappedDoctor;
    }

    public List<DoctorDto> mapToDtoList(List<Doctor> doctors) {
        log.info("Mapping List<Doctor> to List<DoctorDto>");
        List<DoctorDto> doctorDtos = doctors.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return doctorDtos;
    }

    public List<Doctor> mapToEntityList(List<DoctorDto> doctorDtos) {
        log.info("Mapping List<DoctorDto> to List<Doctor>");
        List<Doctor> doctors = doctorDtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        return doctors;
    }

}
