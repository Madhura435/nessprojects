package com.madhura.fileHandling.ness.repository;

import org.springframework.stereotype.Repository;
import com.madhura.fileHandling.ness.model.Filedocument;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * It is FileRepository interface. 
 * @author Madhura
 *<p>It takes two parameters Filedocument class along its primary key id</p>
 */
@Repository
public interface FileRepository extends JpaRepository<Filedocument,Long>{
	Filedocument findByFilename(String fileName);
}
