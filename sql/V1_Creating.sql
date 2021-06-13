-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema docsFilling
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `docsFilling` ;

-- -----------------------------------------------------
-- Schema docsFilling
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `docsFilling` DEFAULT CHARACTER SET utf8 ;
USE `docsFilling` ;

-- -----------------------------------------------------
-- Table `docsFilling`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `docsFilling`.`role` ;

CREATE TABLE IF NOT EXISTS `docsFilling`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `docsFilling`.`person_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `docsFilling`.`person_role` ;

CREATE TABLE IF NOT EXISTS `docsFilling`.`person_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `docsFilling`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `docsFilling`.`address` ;

CREATE TABLE IF NOT EXISTS `docsFilling`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `region` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `building` VARCHAR(45) NOT NULL,
  `flat` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `docsFilling`.`identity_document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `docsFilling`.`identity_document` ;

CREATE TABLE IF NOT EXISTS `docsFilling`.`identity_document` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `series` VARCHAR(45) NULL,
  `number` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `goverment_agency` VARCHAR(100) NOT NULL,
  `identification_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `docsFilling`.`person_identity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `docsFilling`.`person_identity` ;

CREATE TABLE IF NOT EXISTS `docsFilling`.`person_identity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  `person_role_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  `identity_document_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_person_identity_person_role1_idx` (`person_role_id` ASC) VISIBLE,
  INDEX `fk_person_identity_address1_idx` (`address_id` ASC) VISIBLE,
  INDEX `fk_person_identity_identity_document1_idx` (`identity_document_id` ASC) VISIBLE,
  CONSTRAINT `fk_person_identity_person_role1`
    FOREIGN KEY (`person_role_id`)
    REFERENCES `docsFilling`.`person_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_identity_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `docsFilling`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_identity_identity_document1`
    FOREIGN KEY (`identity_document_id`)
    REFERENCES `docsFilling`.`identity_document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `docsFilling`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `docsFilling`.`user` ;

CREATE TABLE IF NOT EXISTS `docsFilling`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL DEFAULT 1,
  `enrollee_id` INT DEFAULT NULL,
  `parent_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_user_role_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_person_identity1_idx` (`enrollee_id` ASC) VISIBLE,
  INDEX `fk_user_person_identity2_idx` (`parent_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `docsFilling`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_person_identity1`
    FOREIGN KEY (`enrollee_id`)
    REFERENCES `docsFilling`.`person_identity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_person_identity2`
    FOREIGN KEY (`parent_id`)
    REFERENCES `docsFilling`.`person_identity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

USE `docsFilling` ;
insert into `role`(title) values ("USER"), ("ADMIN"), ( "CREATOR");
