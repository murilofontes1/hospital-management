DROP TABLE IF EXISTS Administrador;

CREATE TABLE
  Administrador (
    id_adm INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    cpf TEXT NOT NULL UNIQUE,
    telefone TEXT NOT NULL,
    data_nascimento DATE NOT NULL,
    sexo BOOL NOT NULL,
    salario DOUBLE NOT NULL,
    horario_trabalho_inicio TIME NOT NULL,
    horario_trabalho_final TIME NOT NULL,
    login TEXT NOT NULL,
    senha TEXT NOT NULL,
    nome VARCHAR NOT NULL,
    data_admissao DATE NOT NULL DEFAULT CURRENT_DATE
  );

DROP TABLE IF EXISTS Consulta;

CREATE TABLE
  Consulta (
    id_consulta INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    data_consulta DATE NOT NULL,
    hora_consulta TIME NOT NULL,
    sintomas TEXT,
    precisa_internar BOOL NOT NULL,
		diagnostico TEXT,
    id_paciente INTEGER NOT NULL,
    id_medico TEXT NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES Paciente (id_paciente),
    FOREIGN KEY (id_medico) REFERENCES Medico (crm)
  );

DROP TABLE IF EXISTS Enfermaria;

CREATE TABLE Enfermaria (
    id_enfermaria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    qnt_leitos INTEGER NOT NULL,
    leitos_disponiveis INTEGER NOT NULL
);

DROP TABLE IF EXISTS Enfermeiro;

CREATE TABLE
  Enfermeiro (
    id_enfermeiro INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
		id_enfermaria INTEGER,
    cpf TEXT NOT NULL UNIQUE,
    telefone TEXT NOT NULL,
    data_nascimento TEXT NOT NULL,
    sexo BOOL NOT NULL,
    salario DOUBLE NOT NULL,
    horario_trabalho_inicio TIME NOT NULL,
    horario_trabalho_final TIME NOT NULL,
    coren text NOT NULL UNIQUE,
    nome varchar NOT NULL,
    data_admissao DATE NOT NULL DEFAULT CURRENT_DATE,
		FOREIGN KEY (id_enfermaria) REFERENCES Enfermaria (id_enfermaria)
  );

DROP TABLE IF EXISTS Internacao;

CREATE TABLE
  Internacao (
    id_internacao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    data_internacao DATE NOT NULL DEFAULT CURRENT_DATE,
    data_alta DATE,
    id_paciente INTEGER NOT NULL,
    id_enfermaria INTEGER NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES Paciente (id_paciente),
    FOREIGN KEY (id_enfermaria) REFERENCES Enfermaria (id_enfermaria)
  );


DROP TABLE IF EXISTS Medicamento;

CREATE TABLE
  Medicamento (
    id_medicamento INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nome TEXT NOT NULL
  );

DROP TABLE IF EXISTS Medico;

CREATE TABLE Medico (
  id_medico INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  nome TEXT NOT NULL,
  cpf TEXT NOT NULL,
  telefone TEXT NOT NULL,
  data_nascimento DATE NOT NULL,
  sexo BOOL NOT NULL,
  salario REAL NOT NULL,
  data_admissao DATE NOT NULL DEFAULT CURRENT_DATE,
  horario_trabalho_inicio TIME NOT NULL,
  horario_trabalho_final TIME NOT NULL,  
  crm TEXT NOT NULL UNIQUE,
  especialidade TEXT NOT NULL,
  plantao BOOL NOT NULL
);

DROP TABLE IF EXISTS Paciente;

CREATE TABLE Paciente (
  id_paciente INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	nome TEXT NOT NULL,
  cpf TEXT NOT NULL,
  telefone TEXT NOT NULL,
  data_nascimento DATE NOT NULL,
  sexo BOOL NOT NULL,
  internado BOOL NOT NULL,
  plano_saude BOOL NOT NULL
);

DROP TABLE IF EXISTS Prescricao;

CREATE TABLE
  Prescricao (
    id_prescricao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
		id_consulta INTEGER NOT NULL,
    id_medicamento INTEGER NOT NULL,
    dosagem TEXT,
    posologia TEXT,
		FOREIGN KEY (id_consulta) REFERENCES Consulta (id_consulta),
		FOREIGN KEY (id_medicamento) REFERENCES Medicamento (id_medicamento)
  );