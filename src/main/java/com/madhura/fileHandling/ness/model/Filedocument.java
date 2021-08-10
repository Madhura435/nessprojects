package com.madhura.fileHandling.ness.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Thius is Entity class used to store in Database.
 * @author Madhura
 *
 */
@Entity
@Table(name="filedocument")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filedocument {
/**
 * It is auto generated primary key.
 */
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
/**
 * It is name of file which you need to download/upload
 */
@Column(name="filename")
private String filename;
/**
 * It contains large data,file content present here,Array of bytes 
 */
@Column(name="docfile")
@Lob
private byte[] docfile;
}


