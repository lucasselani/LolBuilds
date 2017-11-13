/* alteração de tipo para type */
ALTER TABLE `loldb`.`build` 
CHANGE COLUMN `tipo` `type` VARCHAR(45) NOT NULL ;
/* criando coluna de datetime */
ALTER TABLE build ADD COLUMN `datetime` DATETIME NOT NULL;
