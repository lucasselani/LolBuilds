-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema loldb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema loldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `loldb` DEFAULT CHARACTER SET utf8 ;
USE `loldb` ;

-- -----------------------------------------------------
-- Table `loldb`.`build`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loldb`.`build` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `loldb`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loldb`.`item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `loldb`.`build_has_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loldb`.`build_has_item` (
  `build_id` INT(11) NOT NULL,
  `item_id` INT(11) NOT NULL,
  PRIMARY KEY (`build_id`, `item_id`),
  INDEX `fk_build_has_item_item1_idx` (`item_id` ASC),
  INDEX `fk_build_has_item_build1_idx` (`build_id` ASC),
  CONSTRAINT `fk_build_has_item_build1`
    FOREIGN KEY (`build_id`)
    REFERENCES `loldb`.`build` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_build_has_item_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `loldb`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `loldb`.`champion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loldb`.`champion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `image` VARCHAR(45) NULL DEFAULT NULL,
  `build_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_champion_build1_idx` (`build_id` ASC),
  CONSTRAINT `fk_champion_build1`
    FOREIGN KEY (`build_id`)
    REFERENCES `loldb`.`build` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `loldb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loldb`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(45) NOT NULL,
  `build_id` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_build1_idx` (`build_id` ASC),
  CONSTRAINT `fk_user_build1`
    FOREIGN KEY (`build_id`)
    REFERENCES `loldb`.`build` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `loldb`.`spell`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loldb`.`spell` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `loldb`.`build_has_spell`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loldb`.`build_has_spell` (
  `build_id` INT(11) NOT NULL,
  `spell_id` INT(11) NOT NULL,
  PRIMARY KEY (`build_id`, `spell_id`),
  INDEX `fk_build_has_spell_spell1_idx` (`spell_id` ASC),
  INDEX `fk_build_has_spell_build1_idx` (`build_id` ASC),
  CONSTRAINT `fk_build_has_spell_build1`
    FOREIGN KEY (`build_id`)
    REFERENCES `loldb`.`build` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_build_has_spell_spell1`
    FOREIGN KEY (`spell_id`)
    REFERENCES `loldb`.`spell` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
