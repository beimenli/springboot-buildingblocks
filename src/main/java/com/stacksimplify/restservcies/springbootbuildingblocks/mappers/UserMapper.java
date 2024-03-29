package com.stacksimplify.restservcies.springbootbuildingblocks.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.stacksimplify.restservcies.springbootbuildingblocks.dtos.UserMsDto;
import com.stacksimplify.restservcies.springbootbuildingblocks.entities.User;

@Mapper(componentModel="Spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	//User To UserMsDTO
	@Mappings({
	@Mapping(source="email", target="emailaddress"),
	@Mapping(source="role", target="rolename")
	})
	UserMsDto userToUserDto(User user);
	
	//List<User) to List<UserMsDto>
	List<UserMsDto> usersToUserDtos(List<User> users);
	
}
