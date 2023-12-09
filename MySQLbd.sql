-- Criar o banco de dados
DROP DATABASE IF EXISTS agis;
CREATE DATABASE agis;
USE agis;

-- Tabela aluno (substitua os tipos de dados conforme necessário)
CREATE TABLE aluno (
    ra VARCHAR(9),
    -- Outros campos necessários
);

-- Tabela matricula (substitua os tipos de dados conforme necessário)
CREATE TABLE matricula (
    ano INT,
    semestre VARCHAR(1),
    n1 INT,
    n2 INT,
    n3 INT,
    situacao VARCHAR(255),
    raAluno VARCHAR(9),
    codTurma INT
);

-- Tabela turma (substitua os tipos de dados conforme necessário)
CREATE TABLE turma (
    cod INT,
    -- Outros campos necessários
);

-- Procedimento para gerar RA
DELIMITER //
CREATE PROCEDURE geraRa(OUT ra VARCHAR(9))
BEGIN
    DECLARE anoAtual INT;
    DECLARE semestreAtual VARCHAR(1);
    DECLARE cont INT;

    SET anoAtual = YEAR(NOW());

    IF MONTH(NOW()) > 6 THEN
        SET semestreAtual = '2';
    ELSE
        SET semestreAtual = '1';
    END IF;

    SET ra = CONCAT(anoAtual, semestreAtual);

    SET cont = 0;

    WHILE cont < 4 DO
        SET ra = CONCAT(ra, CAST(FLOOR(RAND() * 10) AS CHAR));
        SET cont = cont + 1;
    END WHILE;
END //
DELIMITER ;

-- Procedimento para calcular data limite
DELIMITER //
CREATE PROCEDURE calculaDataLimite(OUT saida DATE)
BEGIN
    SET saida = DATE_ADD(NOW(), INTERVAL 5 YEAR);
END //
DELIMITER ;

-- Procedimento para validar idade
DELIMITER //
CREATE PROCEDURE validarIdade(dataNasc DATE, OUT saida BIT)
BEGIN
    SET saida = IF(DATEDIFF(NOW(), dataNasc) >= 16, 1, 0);
END //
DELIMITER ;

-- Procedimento para validar CPF
DELIMITER //
CREATE PROCEDURE validarCPF(cpf VARCHAR(11), OUT valido BIT)
BEGIN
    -- Lógica de validação de CPF aqui
    SET valido = 1; -- Substitua pela lógica real
END //
DELIMITER ;

-- Gatilho para matriculaSemestreUm
DROP TRIGGER matriculaSemestreUm;
DELIMITER //

CREATE TRIGGER matriculaSemestreUm
AFTER INSERT ON aluno
FOR EACH ROW
BEGIN
    INSERT INTO matricula(ano, semestre, n1, n2, n3, situacao, ra_aluno, cod_turma)
    SELECT 
        YEAR(NOW()) as ano,
        CASE WHEN MONTH(NOW()) > 6 THEN '2' ELSE '1' END as semestre,
        0,
        0,
        0,
        'cursando',
        NEW.ra,
        t.cod
    FROM disciplina d
    INNER JOIN turma t ON t.cod_disciplina = d.cod
    WHERE d.semestre = 1 AND NEW.cod_curso = d.cod_curso;
END //

DELIMITER ;
