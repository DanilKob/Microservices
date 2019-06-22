package home.project.bookingapp.dto.mapper;

import org.mapstruct.factory.Mappers;

public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}
