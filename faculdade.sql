DROP DATABASE IF EXISTS faculdade;
CREATE DATABASE faculdade;
USE faculdade;

CREATE TABLE supervisor(
	cpf LONG PRIMARY KEY,
    nome TEXT NOT NULL,
    email VARCHAR(30)
);

CREATE TABLE sala(
	numero INT PRIMARY KEY,
    vagas INT,
    cpf_supervisor INT,
    
    CONSTRAINT fk1
    FOREIGN KEY (cpf_supervisor) REFERENCES supervisor(cpf) -- Arrumar depois
);

CREATE TABLE vestibulando(
	cpf LONG PRIMARY KEY,
    nome TEXT NOT NULL,
    email VARCHAR(30),
    numero_sala INT,
    
    CONSTRAINT fk1
    FOREIGN KEY (numero_sala) REFERENCES sala(numero) -- Ver depois
);

CREATE TABLE vestibular(
	id INT AUTO_INCREMENT PRIMARY KEY,
	localizacao TEXT NOT NULL,
    data_realizacao DATE NOT NULL
);

CREATE TABLE materia(
	id_vestibular INT,
    materia VARCHAR(15) NOT NULL,
    
    CONSTRAINT fk1
    FOREIGN KEY (id_vestibular) REFERENCES vestibular(id) -- Ver depois
);

CREATE TABLE vestibulando_presta_vestibular(
	id_vestibular INT,
    cpf_vestibulando LONG,
    numero_inscricao INT,
    
    CONSTRAINT fk1
    FOREIGN KEY (id_vestibular) REFERENCES vestibular(id) -- Arrumar depois
    , 
    CONSTRAINT fk2 
    FOREIGN KEY (cpf_vestibulando) REFERENCES vestibulando(cpf) -- Arrumar depois
    ,
    PRIMARY KEY(id_vestibular, cpf_vestibulando)
);

CREATE TABLE curso(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome TEXT
);

CREATE TABLE curso_oferece_vestibular(
	id_vestibular INT,
    id_curso INT,
    vagas INT NOT NULL,
    
    CONSTRAINT fk1
    FOREIGN KEY (id_vestibular) REFERENCES vestibular(id) -- Arrumar depois
    , 
    CONSTRAINT
    FOREIGN KEY (id_curso) REFERENCES curso(id) -- Arrumar depois
    ,
    PRIMARY KEY(id_vestibular, id_curso)
);

CREATE TABLE prova(
	id INT AUTO_INCREMENT PRIMARY KEY,
    edicao INT NOT NULL,
    cpf_corretor LONG,
    
    CONSTRAINT
    FOREIGN KEY (cpf_corretor) REFERENCES corretor(cpf) -- Arrumar depois
);

CREATE TABLE vestibulando_realiza_prova(
	cpf_vestibulando LONG,
    id_prova INT,
    nota FLOAT,
    
    CONSTRAINT
    FOREIGN KEY (cpf_vestibulando) REFERENCES vestibulando(cpf) -- Arrumar depois
    ,
    CONSTRAINT
    FOREIGN KEY (id_prova) REFERENCES prova(id) -- Arrumar depois
    ,
    PRIMARY KEY(cpf_vestibulando, id_prova)
);

CREATE TABLE corretor(
	cpf LONG PRIMARY KEY,
    nome TEXT NOT NULL,
    email VARCHAR(30)
);
