package com.personal.utility.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.utility.entity.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, String> {

}
