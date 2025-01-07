-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema myyebook
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema myyebook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myyebook` DEFAULT CHARACTER SET utf8mb3 ;
USE `myyebook` ;

-- -----------------------------------------------------
-- Table `myyebook`.`auteur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myyebook`.`auteur` (
  `AUT_ID` INT NOT NULL,
  `AUT_NOM` VARCHAR(45) NOT NULL,
  `AUT_PRENOM` VARCHAR(45) NOT NULL,
  `AUT_IMG` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`AUT_ID`),
  UNIQUE INDEX `AUT_ID_UNIQUE` (`AUT_ID` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myyebook`.`catagorie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myyebook`.`catagorie` (
  `CAT_ID` INT NOT NULL,
  `CAT_LIBELLE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CAT_ID`),
  UNIQUE INDEX `CAT_ID_UNIQUE` (`CAT_ID` ASC) VISIBLE,
  UNIQUE INDEX `CAT_LIBELLE_UNIQUE` (`CAT_LIBELLE` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myyebook`.`compte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myyebook`.`compte` (
  `CPT_ID` INT NOT NULL,
  `CPT_LOGIN` CHAR(10) NOT NULL,
  `CPT_PASS` CHAR(255) NOT NULL,
  `CPT_ROLE` SET('LIBRAIRE', 'CLIENT') NOT NULL,
  PRIMARY KEY (`CPT_ID`),
  UNIQUE INDEX `CPT_ID_UNIQUE` (`CPT_ID` ASC) VISIBLE,
  UNIQUE INDEX `CPT_LOGIN_UNIQUE` (`CPT_LOGIN` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myyebook`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myyebook`.`client` (
  `CLI_ID` INT NOT NULL,
  `CLI_NOM` VARCHAR(45) NOT NULL,
  `CLI_PRENOM` VARCHAR(45) NOT NULL,
  `CLI_EMAIL` VARCHAR(100) NOT NULL,
  `COMPTE_SEC_ID` INT NOT NULL,
  PRIMARY KEY (`CLI_ID`),
  UNIQUE INDEX `CLI_ID_UNIQUE` (`CLI_ID` ASC) VISIBLE,
  INDEX `fk_CLIENT_COMPTE1_idx` (`COMPTE_SEC_ID` ASC) VISIBLE,
  CONSTRAINT `fk_CLIENT_COMPTE1`
    FOREIGN KEY (`COMPTE_SEC_ID`)
    REFERENCES `myyebook`.`compte` (`CPT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myyebook`.`libraire`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myyebook`.`libraire` (
  `LIB_ID` INT NOT NULL,
  `LIB_NOM` VARCHAR(45) NOT NULL,
  `LIB_PRENOM` VARCHAR(45) NOT NULL,
  `COMPTE_SEC_ID` INT NOT NULL,
  PRIMARY KEY (`LIB_ID`),
  UNIQUE INDEX `LIB_ID_UNIQUE` (`LIB_ID` ASC) VISIBLE,
  INDEX `fk_LIBRAIRE_COMPTE1_idx` (`COMPTE_SEC_ID` ASC) VISIBLE,
  CONSTRAINT `fk_LIBRAIRE_COMPTE1`
    FOREIGN KEY (`COMPTE_SEC_ID`)
    REFERENCES `myyebook`.`compte` (`CPT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myyebook`.`livre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myyebook`.`livre` (
  `LIV_ID` INT NOT NULL,
  `LIV_TITRE` VARCHAR(45) NOT NULL,
  `LIV_RESUME` TEXT NOT NULL,
  `LIV_IMAGE` LONGBLOB NULL DEFAULT NULL,
  `LIV_QUANTITE` INT NOT NULL,
  `AUTEUR_AUT_ID` INT NOT NULL,
  `CATAGORIE_CAT_ID` INT NOT NULL,
  PRIMARY KEY (`LIV_ID`),
  UNIQUE INDEX `LIV_ID_UNIQUE` (`LIV_ID` ASC) VISIBLE,
  INDEX `fk_LIVRE_AUTEUR1_idx` (`AUTEUR_AUT_ID` ASC) VISIBLE,
  INDEX `fk_LIVRE_CATAGORIE1_idx` (`CATAGORIE_CAT_ID` ASC) VISIBLE,
  CONSTRAINT `fk_LIVRE_AUTEUR1`
    FOREIGN KEY (`AUTEUR_AUT_ID`)
    REFERENCES `myyebook`.`auteur` (`AUT_ID`),
  CONSTRAINT `fk_LIVRE_CATAGORIE1`
    FOREIGN KEY (`CATAGORIE_CAT_ID`)
    REFERENCES `myyebook`.`catagorie` (`CAT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myyebook`.`emprunt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myyebook`.`emprunt` (
  `CLIENT_CLI_ID` INT NOT NULL,
  `LIVRE_LIV_ID` INT NOT NULL,
  `LIBRAIRE_LIB_ID` INT NOT NULL,
  PRIMARY KEY (`CLIENT_CLI_ID`, `LIVRE_LIV_ID`, `LIBRAIRE_LIB_ID`),
  INDEX `fk_CLIENT_has_LIVRE_LIVRE1_idx` (`LIVRE_LIV_ID` ASC) VISIBLE,
  INDEX `fk_CLIENT_has_LIVRE_CLIENT_idx` (`CLIENT_CLI_ID` ASC) VISIBLE,
  INDEX `fk_CLIENT_has_LIVRE_LIBRAIRE1_idx` (`LIBRAIRE_LIB_ID` ASC) VISIBLE,
  CONSTRAINT `fk_CLIENT_has_LIVRE_CLIENT`
    FOREIGN KEY (`CLIENT_CLI_ID`)
    REFERENCES `myyebook`.`client` (`CLI_ID`),
  CONSTRAINT `fk_CLIENT_has_LIVRE_LIBRAIRE1`
    FOREIGN KEY (`LIBRAIRE_LIB_ID`)
    REFERENCES `myyebook`.`libraire` (`LIB_ID`),
  CONSTRAINT `fk_CLIENT_has_LIVRE_LIVRE1`
    FOREIGN KEY (`LIVRE_LIV_ID`)
    REFERENCES `myyebook`.`livre` (`LIV_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
