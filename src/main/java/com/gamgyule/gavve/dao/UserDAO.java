package com.gamgyule.gavve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gamgyule.gavve.dto.UserDTO;

@Mapper
public interface UserDAO {
    List<UserDTO> getUserList();
}
