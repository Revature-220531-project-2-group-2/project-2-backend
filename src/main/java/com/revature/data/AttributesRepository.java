package com.revature.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Attribute;

@Repository
public interface AttributesRepository extends JpaRepository<Attribute, Integer>{

}
