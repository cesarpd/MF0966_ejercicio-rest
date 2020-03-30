CREATE DEFINER=`root`@`localhost` PROCEDURE `resenasGetById`(IN `rcodigo` INT)
BEGIN
SELECT r.codigo, r.texto, a.codigo, a.nombre, a.apellidos,c.codigo, c.nombre, c.identificador,c.nHoras, p.codigo, p.nombre, p.apellidos
FROM resena r
join alumno a on a.codigo = r.alumno_codigo
join imparticion i on i.resena_codigo = r.codigo
join curso c on i.curso_codigo = c.codigo
join profesor p on c.profesor_codigo = p.codigo
where r.codigo = rcodigo
;
END