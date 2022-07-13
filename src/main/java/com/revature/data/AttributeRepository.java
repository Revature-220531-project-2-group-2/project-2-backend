package com.revature.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
	
public Attribute findAttributeByUserId(int id);
	
	public Set<Attribute> findAllAttributeByUserId(int id);
	
	public Set<Attribute> findAttributeByUserCharSheet(String charsheet);

}
