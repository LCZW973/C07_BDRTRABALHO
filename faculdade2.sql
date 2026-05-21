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

