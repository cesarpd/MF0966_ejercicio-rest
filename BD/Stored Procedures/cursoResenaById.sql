CREATE DEFINER=`root`@`localhost` PROCEDURE `cursoResenaById`(IN `ccodigo` INT)
BEGIN
	SELECT c.codigo, c.nombre, c.identificador,c.nHoras, p.codigo, p.nombre, p.apellidos, a.codigo, a.nombre, a.apellidos, r.codigo, r.texto
	FROM curso c
	JOIN imparticion i ON c.codigo = i.curso_codigo
	JOIN resena r ON r.codigo = i.resena_codigo
	JOIN profesor p ON p.codigo = c.profesor_codigo
    JOIN alumno a ON a.codigo = i.alumno_codigo
    WHERE c.codigo = ccodigo
    ;
END