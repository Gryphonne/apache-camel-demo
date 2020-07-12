package com.bg.payprocessor.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bg.payprocessor.domain.ValidMessage;

public interface PersistenceRepository extends JpaRepository<ValidMessage, Serializable> {

}
