CREATE DEFINER=`root`@`localhost` PROCEDURE `cursoResenagetAll`()
BEGIN
	SELECT c.codigo, c.nombre, c.identificador,c.nHoras, p.codigo, p.nombre, p.apellidos,r.codigo, r.texto
	FROM curso c
	JOIN imparticion i ON c.codigo = i.curso_codigo
	JOIN resena r ON r.codigo = i.resena_codigo
	JOIN profesor p ON p.codigo = c.profesor_codigo
    ;
END