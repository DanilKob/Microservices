package home.project.bookingapp.dto.mapper;

import home.project.bookingapp.dto.UserDTO;
import home.project.bookingapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "login", target = "login")
    UserDTO userToUserDTO(User user);

    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "login", target = "login")
    User userDTOToUser(UserDTO userDTO);
}
