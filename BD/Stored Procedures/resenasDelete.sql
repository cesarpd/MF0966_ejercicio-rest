CREATE DEFINER=`root`@`localhost` PROCEDURE `resenasDelete`(IN `rcodigo` INT)
BEGIN
DELETE FROM imparticion
WHERE resena_codigo = rcodigo
;
END