CREATE DEFINER=`root`@`localhost` PROCEDURE `resenasInsert`(IN `rtexto` longtext,`racodigo` INT, `ccodigo` INT,  OUT `rcodigo` INT)
BEGIN
	INSERT INTO `resena` ( 
        `texto`, 
        `alumno_codigo`
        ) 
        VALUES (
		rtexto,
		racodigo
        
        );  
	
    SET rcodigo = LAST_INSERT_ID();
    
    INSERT INTO `imparticion` ( 
        `curso_codigo`, 
        `alumno_codigo`,
        `resena_codigo`
        ) 
        VALUES (
		ccodigo,
		racodigo,
        rcodigo
        
        );           

END