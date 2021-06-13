-- MySQL Script generated by MySQL Workbench
-- Mon May 17 08:33:45 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `testdocs` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testdocs` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema testdocs
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `testdocs` ;

-- -----------------------------------------------------
-- Schema testdocs
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testdocs` DEFAULT CHARACTER SET utf8 ;
USE `testdocs` ;

-- -----------------------------------------------------
-- Table `mydb`.`identify_document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdocs`.`identify_document` ;

CREATE TABLE IF NOT EXISTS `testdocs`.`identify_document` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `government_agency` VARCHAR(255) NOT NULL,
  `identification_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `testdocs` ;

-- -----------------------------------------------------
-- Table `testdocs`.`personal_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdocs`.`personal_info` ;

CREATE TABLE IF NOT EXISTS `testdocs`.`personal_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `patronymic` VARCHAR(255) NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `testdocs` ;

-- -----------------------------------------------------
-- Table `testdocs`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdocs`.`address` ;

CREATE TABLE IF NOT EXISTS `testdocs`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(255) NOT NULL,
  `region` VARCHAR(255) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `building` VARCHAR(255) NOT NULL,
  `flat` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

USE `testdocs` ;

-- -----------------------------------------------------
-- Table `testdocs`.`doc_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdocs`.`doc_info` ;

CREATE TABLE IF NOT EXISTS `testdocs`.`doc_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  
  `personal_info_id` INT NULL,
  `parent_personal_info_id` INT NULL,
  
  `identify_document_id` INT NULL,
  `parent_identify_document_id` INT NULL,
  
  `address_id` INT NULL,
  `parent_address_id` INT NULL,
  
  `is_eighteen` bool NOT NULL,
 
  PRIMARY KEY (`id`),
  
  INDEX `fk_doc_info_personal_info_idx` (`personal_info_id` ASC) VISIBLE,
  INDEX `fk_doc_info_personal_info2_idx` (`parent_personal_info_id` ASC) VISIBLE,
  
  INDEX `fk_doc_info_identify_document_idx` (`identify_document_id` ASC) VISIBLE,
  INDEX `fk_doc_info_identify_document2_idx` (`parent_identify_document_id` ASC) VISIBLE,
  
  INDEX `fk_doc_info_address1_idx` (`address_id` ASC) VISIBLE,
  INDEX `fk_doc_info_address2_idx` (`parent_address_id` ASC) VISIBLE,
  
  CONSTRAINT `fk_doc_info_personal_info`
    FOREIGN KEY (`personal_info_id`)
    REFERENCES `testdocs`.`personal_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    
CONSTRAINT `fk_doc_info_personal_info2`
    FOREIGN KEY (`parent_personal_info_id`)
    REFERENCES `testdocs`.`personal_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  
  CONSTRAINT `fk_doc_info_identify_document`
    FOREIGN KEY (`identify_document_id`)
    REFERENCES `testdocs`.`identify_document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    
CONSTRAINT `fk_doc_info_identify_document2`
    FOREIGN KEY (`parent_identify_document_id`)
    REFERENCES `testdocs`.`identify_document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    
CONSTRAINT `fk_doc_info_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `testdocs`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    
CONSTRAINT `fk_doc_info_address2`
    FOREIGN KEY (`parent_address_id`)
    REFERENCES `testdocs`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `testdocs` ;

-- -----------------------------------------------------
-- Table `testdocs`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdocs`.`user` ;

CREATE TABLE IF NOT EXISTS `testdocs`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'USER',
  `status` VARCHAR(45) NULL DEFAULT 'ACTIVE',
  `doc_info_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_user_doc_info_idx` (`doc_info_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_doc_info`
    FOREIGN KEY (`doc_info_id`)
    REFERENCES `testdocs`.`doc_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO doc_info (id, is_eighteen)
VALUES (1, false),(2, false);

INSERT INTO user (login, password, role, status, doc_info_id)
VALUES ('admin', '$2y$12$HT0O3sEypl13YuSr4kz5beeuMxFV85wINN.kxzVHEy4TYCdVbWUES', 'ADMIN', 'ACTIVE', 1), 
		('user', '$2y$12$LkUFuzM0ZpcbQiEggNb/E.qOmjjuTvzULdHExK7XLziHT9r/iAr4G', 'USER', 'ACTIVE', 2);
