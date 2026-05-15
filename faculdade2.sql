DROP DATABASE IF EXISTS faculdade;
CREATE DATABASE faculdade;
USE faculdade;


-- Criar no mínimo 5 tabelas, incluindo atributos e relacionamentos coesos entre elas --
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

-- Criar 2 usuários arbitrários (sua escolha para nome e senha) --
CREATE USER 'Elton'@'%' IDENTIFIED BY '00o00';
CREATE USER 'Elias'@'%' IDENTIFIED BY '00i00';

-- Criar 1 Role, garantir no mínimo 2 privilégios e inserir os usuários nela por padrão --
CREATE ROLE 'Insert_select';
GRANT INSERT,SELECT ON faculdade.* TO 'Insert_select';
GRANT  'Insert_select' TO 'Elton'@'%';
GRANT  'Insert_select' TO 'Elias'@'%';
SET DEFAULT ROLE  'Insert_select'  TO 'Elton'@'%';
SET DEFAULT ROLE  'Insert_select'  TO 'Elias'@'%';
