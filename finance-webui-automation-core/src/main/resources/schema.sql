-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema deposit
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `deposit`;

-- -----------------------------------------------------
-- Schema deposit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `deposit` DEFAULT CHARACTER SET utf8;
USE `deposit`;

-- -----------------------------------------------------
-- Table `deposit`.`currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deposit`.`currency`;

CREATE TABLE IF NOT EXISTS `deposit`.`currency`
(
    `id`   INT        NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(3) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    UNIQUE INDEX `code_UNIQUE` (`code` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deposit`.`term`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deposit`.`term`;

CREATE TABLE IF NOT EXISTS `deposit`.`term`
(
    `id`          INT        NOT NULL AUTO_INCREMENT,
    `start_date`  DATE       NOT NULL,
    `period`      INT        NOT NULL,
    `period_type` VARCHAR(6) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deposit`.`adding_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deposit`.`adding_type`;

CREATE TABLE IF NOT EXISTS `deposit`.`adding_type`
(
    `id`   INT        NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(9) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deposit`.`deposit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deposit`.`deposit`;

CREATE TABLE IF NOT EXISTS `deposit`.`deposit`
(
    `id`                     INT            NOT NULL AUTO_INCREMENT,
    `initial_sum`            DECIMAL(10, 2) NOT NULL,
    `interest_rate`          DECIMAL(5, 2)  NOT NULL,
    `currency_id`            INT            NOT NULL,
    `term_id`                INT            NOT NULL,
    `replenishment_type_id`  INT            NULL,
    `replenishment_sum`      DECIMAL(10, 2) NULL,
    `capitalization_type_id` INT            NULL,
    PRIMARY KEY (`id`, `currency_id`, `term_id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `fk_deposit_currency_idx` (`currency_id` ASC),
    INDEX `fk_deposit_term1_idx` (`term_id` ASC),
    INDEX `fk_deposit_adding_type1_idx` (`replenishment_type_id` ASC),
    INDEX `fk_deposit_adding_type2_idx` (`capitalization_type_id` ASC),
    CONSTRAINT `fk_deposit_currency`
        FOREIGN KEY (`currency_id`)
            REFERENCES `deposit`.`currency` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_deposit_term1`
        FOREIGN KEY (`term_id`)
            REFERENCES `deposit`.`term` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_deposit_adding_type1`
        FOREIGN KEY (`replenishment_type_id`)
            REFERENCES `deposit`.`adding_type` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_deposit_adding_type2`
        FOREIGN KEY (`capitalization_type_id`)
            REFERENCES `deposit`.`adding_type` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
