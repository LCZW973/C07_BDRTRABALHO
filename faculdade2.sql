DROP DATABASE IF EXISTS faculdade;
CREATE DATABASE faculdade;
USE faculdade;

-- CRIAR NO MINIMO 5 TABELAS E RELACIONAMENTOS COESES ENTRE ELAS --
CREATE TABLE supervisor(
    cpf BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(30)
);

CREATE TABLE sala(
    numero INT PRIMARY KEY,
    vagas INT,
    cpf_supervisor BIGINT,

    CONSTRAINT fk_sala_supervisor
    FOREIGN KEY (cpf_supervisor)
    REFERENCES supervisor(cpf)
);

CREATE TABLE vestibulando(
    cpf BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(30),
    numero_sala INT,

    CONSTRAINT fk_vestibulando_sala
    FOREIGN KEY (numero_sala)
    REFERENCES sala(numero)
);

CREATE TABLE vestibular(
    id INT AUTO_INCREMENT PRIMARY KEY,
    localizacao VARCHAR(100) NOT NULL,
    data_realizacao DATE NOT NULL
);

CREATE TABLE materia(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_vestibular INT,
    materia VARCHAR(15) NOT NULL,

    CONSTRAINT fk_materia_vestibular
    FOREIGN KEY (id_vestibular)
    REFERENCES vestibular(id)
);

CREATE TABLE vestibulando_presta_vestibular(
    id_vestibular INT,
    cpf_vestibulando BIGINT,
    numero_inscricao INT,

    PRIMARY KEY(id_vestibular, cpf_vestibulando),

    CONSTRAINT fk_vpv_vestibular
    FOREIGN KEY (id_vestibular)
    REFERENCES vestibular(id),

    CONSTRAINT fk_vpv_vestibulando
    FOREIGN KEY (cpf_vestibulando)
    REFERENCES vestibulando(cpf)
);

CREATE TABLE curso(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE curso_oferece_vestibular(
    id_vestibular INT,
    id_curso INT,
    vagas INT NOT NULL,

    PRIMARY KEY(id_vestibular, id_curso),

    CONSTRAINT fk_cov_vestibular
    FOREIGN KEY (id_vestibular)
    REFERENCES vestibular(id),

    CONSTRAINT fk_cov_curso
    FOREIGN KEY (id_curso)
    REFERENCES curso(id)
);

CREATE TABLE corretor(
    cpf BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(30)
);

CREATE TABLE prova(
    id INT AUTO_INCREMENT PRIMARY KEY,
    edicao INT NOT NULL,
    cpf_corretor BIGINT,

    CONSTRAINT fk_prova_corretor
    FOREIGN KEY (cpf_corretor)
    REFERENCES corretor(cpf)
);

CREATE TABLE vestibulando_realiza_prova(
    cpf_vestibulando BIGINT,
    id_prova INT,
    nota FLOAT,

    PRIMARY KEY(cpf_vestibulando, id_prova),

    CONSTRAINT fk_vrp_vestibulando
    FOREIGN KEY (cpf_vestibulando)
    REFERENCES vestibulando(cpf),

    CONSTRAINT fk_vrp_prova
    FOREIGN KEY (id_prova)
    REFERENCES prova(id)
);

-- INSERIR NO MINIMO 5REGISTROS PARA CADA TABELA --
-- SUPERVISOR--
INSERT INTO supervisor VALUES
(11111111111,'Carlos Silva','carlos@gmail.com'),
(22222222222,'Ana Souza','ana@gmail.com'),
(33333333333,'Marcos Lima','marcos@gmail.com'),
(44444444444,'Fernanda Alves','fernanda@gmail.com'),
(55555555555,'Joao Rocha','joao@gmail.com');
-- SALA --
INSERT INTO sala VALUES
(101,40,11111111111),
(102,35,22222222222),
(103,50,33333333333),
(104,45,44444444444),
(105,30,55555555555);
-- VESTIBULANDO --
INSERT INTO vestibulando VALUES
(99911111111,'Lucas Pereira','lucas@gmail.com',101),
(99922222222,'Mariana Costa','mariana@gmail.com',102),
(99933333333,'Pedro Henrique','pedro@gmail.com',103),
(99944444444,'Julia Martins','julia@gmail.com',104),
(99955555555,'Gabriel Souza','gabriel@gmail.com',105);
-- VESTIBULAR --
INSERT INTO vestibular(localizacao,data_realizacao) VALUES
('Sao Paulo','2026-06-10'),
('Rio de Janeiro','2026-07-15'),
('Curitiba','2026-08-20'),
('Belo Horizonte','2026-09-12'),
('Porto Alegre','2026-10-05');
-- MATERIA --
INSERT INTO materia(id_vestibular,materia) VALUES
(1,'Matematica'),
(2,'Fisica'),
(3,'Quimica'),
(4,'Historia'),
(5,'Biologia');
-- VESTIBULANDO_PRESTA_VESTIBULAR --
INSERT INTO vestibulando_presta_vestibular VALUES
(1,99911111111,1001),
(2,99922222222,1002),
(3,99933333333,1003),
(4,99944444444,1004),
(5,99955555555,1005);
-- CURSO --
INSERT INTO curso(nome) VALUES
('Engenharia'),
('Medicina'),
('Direito'),
('Computacao'),
('Arquitetura');
-- CURSO_OFERECE_VESTIBULAR --
INSERT INTO curso_oferece_vestibular VALUES
(1,1,50),
(2,2,40),
(3,3,60),
(4,4,45),
(5,5,35);
-- CORRETOR --
INSERT INTO corretor VALUES
(88811111111,'Ricardo Gomes','ricardo@gmail.com'),
(88822222222,'Patricia Lima','patricia@gmail.com'),
(88833333333,'Anderson Silva','anderson@gmail.com'),
(88844444444,'Vanessa Costa','vanessa@gmail.com'),
(88855555555,'Roberta Souza','roberta@gmail.com');
-- PROVA --
INSERT INTO prova(edicao,cpf_corretor) VALUES
(2021,88811111111),
(2022,88822222222),
(2023,88833333333),
(2024,88844444444),
(2025,88855555555);
-- VESTIBULANDO_REALIZA_PROVA --
INSERT INTO vestibulando_realiza_prova VALUES
(99911111111,1,8.5),
(99922222222,2,7.0),
(99933333333,3,9.2),
(99944444444,4,6.8),
(99955555555,5,8.9);

-- CRIAR 2 USARUIS ARBITRARIOS--
CREATE USER 'Elton'@'%' IDENTIFIED BY '00o00';
CREATE USER 'Elias'@'%' IDENTIFIED BY '00i00';

-- CRIAR1 ROLE E GARANTIR NO MINIMO 2 PRIVILEGIOD E INSERIR USARRIOS NELA POR PADRAO --
CREATE ROLE 'Insert_select';
GRANT INSERT,SELECT ON faculdade.* TO 'Insert_select';
GRANT  'Insert_select' TO 'Elton'@'%';
GRANT  'Insert_select' TO 'Elias'@'%';
SET DEFAULT ROLE  'Insert_select'  TO 'Elton'@'%';
SET DEFAULT ROLE  'Insert_select'  TO 'Elias'@'%';


-- FUNCAO QUE CONTA AS VAGAS DE TODAS AS SALAS--
DELIMITER $$
DROP FUNCTION IF EXISTS contavagas $$
CREATE FUNCTION contavagas () 
RETURNS INT 
DETERMINISTIC
BEGIN

    DECLARE vagasTotais INT;

    SELECT SUM(vagas)
    INTO vagasTotais
    FROM sala;

    RETURN vagasTotais;

END$$
DELIMITER ;

-- FUNCAO QUE CALCULA O NUMERO DE VESTIBULANDO ATUAIS --
DELIMITER $$
DROP FUNCTION IF EXISTS contavestibulandos $$

CREATE FUNCTION contavestibulandos () 
RETURNS INT 
DETERMINISTIC
BEGIN

    DECLARE numerovestibulandos INT;

    SELECT COUNT(cpf)
    INTO numerovestibulandos
    FROM vestibulando;

    RETURN numerovestibulandos;

END$$
DELIMITER ;

-- TRIGGER QUE USA AS FUNCOES ANTERIORES PARA CONTROLAR O NUMERO DE VESTIBULANDOS DE ACORDOS COM AS VAGAS--
DELIMITER $$
CREATE TRIGGER controlavagas
BEFORE INSERT ON vestibulando
FOR EACH ROW
BEGIN

    DECLARE vagasTotais INT;
    DECLARE vestibulandos INT;

    SET vagasTotais = contavagas();
    SET vestibulandos = contavestibulandos();

    IF vestibulandos >= vagasTotais THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Sem vagas disponiveis';
    END IF;

END$$
DELIMITER ;

SELECT * FROM vestibulando ;
