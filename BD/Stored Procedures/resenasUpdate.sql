CREATE DEFINER=`root`@`localhost` PROCEDURE `resenasUpdate`(IN `rcodigo` INT, IN `rtexto` longtext,`acodigo` INT, `ccodigo` INT)
BEGIN
	UPDATE `resena`  
	SET
	`texto` = rtexto,
	`alumno_codigo` = acodigo
	WHERE codigo = rcodigo
	;
	UPDATE `imparticion`
	SET
	`curso_codigo` = ccodigo, 
	`alumno_codigo` = acodigo
	WHERE
	resena_codigo = rcodigo
	; 
END