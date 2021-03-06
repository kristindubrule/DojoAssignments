-- MySQL Script generated by MySQL Workbench
-- Mon Feb 12 13:29:34 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema user_dashboard
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `user_dashboard` ;

-- -----------------------------------------------------
-- Schema user_dashboard
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `user_dashboard` DEFAULT CHARACTER SET utf8 ;
USE `user_dashboard` ;

-- -----------------------------------------------------
-- Table `user_dashboard`.`user_levels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_dashboard`.`user_levels` ;

CREATE TABLE IF NOT EXISTS `user_dashboard`.`user_levels` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `level` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_dashboard`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_dashboard`.`users` ;

CREATE TABLE IF NOT EXISTS `user_dashboard`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email_address` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `user_level_id` TINYINT NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `users_level_id_idx` (`user_level_id` ASC),
  CONSTRAINT `users_level_id`
    FOREIGN KEY (`user_level_id`)
    REFERENCES `user_dashboard`.`user_levels` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_dashboard`.`messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_dashboard`.`messages` ;

CREATE TABLE IF NOT EXISTS `user_dashboard`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `target_user_id` VARCHAR(45) NULL,
  `content` LONGTEXT NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  INDEX `user_id_idx` (`user_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `blogs_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_dashboard`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_dashboard`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_dashboard`.`comments` ;

CREATE TABLE IF NOT EXISTS `user_dashboard`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` LONGTEXT NULL,
  `user_id` INT NULL,
  `message_id` INT NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  INDEX `post_id_idx` (`message_id` ASC),
  CONSTRAINT `messages_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_dashboard`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `messages_blog_id`
    FOREIGN KEY (`message_id`)
    REFERENCES `user_dashboard`.`messages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_dashboard`.`logins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_dashboard`.`logins` ;

CREATE TABLE IF NOT EXISTS `user_dashboard`.`logins` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `logged_in` DATETIME NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `logins_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_dashboard`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
