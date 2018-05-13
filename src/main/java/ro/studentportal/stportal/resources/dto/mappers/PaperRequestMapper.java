package ro.studentportal.stportal.resources.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.studentportal.stportal.model.PaperRequest;
import ro.studentportal.stportal.resources.dto.PaperRequestDto;
import ro.studentportal.stportal.resources.dto.PaperRequestProcessDto;

@Mapper
public abstract class PaperRequestMapper {
    public static PaperRequestMapper INSTANCE = Mappers.getMapper(PaperRequestMapper.class);

    public abstract PaperRequestDto toDto(PaperRequest paperRequest);
    public abstract PaperRequest toModel(PaperRequestDto paperRequestDto);

    public PaperRequestProcessDto toProcessDto(PaperRequest paperRequest){
        PaperRequestProcessDto paperRequestProcessDto =new PaperRequestProcessDto();
        paperRequestProcessDto.setId(paperRequest.getId());
        paperRequestProcessDto.setDate(paperRequest.getDate().toString());
        paperRequestProcessDto.setDescription(paperRequest.getDescription());
        paperRequestProcessDto.setStatus(paperRequest.isFlag());
        paperRequestProcessDto.setStudentName(paperRequest.getStudent().toPrettyName());
        paperRequestProcessDto.setStudentYear(Long.valueOf(paperRequest.getStudent().getYearOfStudy()));
        return paperRequestProcessDto;
    }

}
