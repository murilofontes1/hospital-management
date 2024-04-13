CREATE TABLE Administrador (
  id_adm INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  cpf TEXT NOT NULL,
  telefone TEXT NOT NULL,
  data_nascimento DATE NOT NULL,
  sexo BOOL NOT NULL,
  salario DOUBLE NOT NULL,
  inicio_expediente TIME NOT NULL,
  fim_expediente TIME NOT NULL,
  login TEXT NOT NULL,
  senha TEXT NOT NULL
);

CREATE TABLE Medico (
  id_medico INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  cpf TEXT NOT NULL,
  telefone TEXT NOT NULL,
  data_nascimento DATE NOT NULL,
  sexo BOOL NOT NULL,
  salario DOUBLE NOT NULL,
  inicio_expediente TIME NOT NULL,
  fim_expediente TIME NOT NULL,
  crm TEXT NOT NULL,
  especialidade TEXT,
  plantao BOOL NOT NULL,
  consultas_realizadas TEXT
);

CREATE TABLE Enfermeiro (
  id_enfermeiro INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  cpf TEXT NOT NULL,
  telefone TEXT NOT NULL,
  data_nascimento DATE NOT NULL,
  sexo BOOL NOT NULL,
  salario DOUBLE NOT NULL,
  inicio_expediente TIME NOT NULL,
  fim_expediente TIME NOT NULL,
  coren TEXT NOT NULL
);

CREATE TABLE Paciente (
  id_paciente INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  cpf TEXT NOT NULL,
  telefone TEXT NOT NULL,
  data_nascimento DATE NOT NULL,
  sexo BOOL NOT NULL,
  internado BOOL NOT NULL,
  plano_saude BOOL NOT NULL
);

CREATE TABLE Enfermaria (
  id_enfermaria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  qtd_leitos INTEGER,
  leitos_disponiveis INTEGER,
  id_enfermeiro INTEGER,
  FOREIGN KEY (id_enfermeiro) REFERENCES Enfermeiro(id_enfermeiro)
);

CREATE TABLE Internacao (
  id_internacao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  data_internacao DATE NOT NULL,
  dat_alta DATE,
  id_paciente INTEGER,
  id_enfermaria INTEGER,
  FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente),
  FOREIGN KEY (id_enfermaria) REFERENCES Enfermaria(id_enfermaria)
);

CREATE TABLE Consulta (
  id_consulta INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  data_consulta DATE NOT NULL,
  hora_consulta TIME,
  sintomas TEXT,
  precisa_internar BOOL,
  encaminhamento TEXT,
  id_paciente INTEGER,
  id_medico INTEGER,
  FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente),
  FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

CREATE TABLE Prescricao (
  id_prescricao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  medicacao TEXT,
  dosagem TEXT,
  posologia TEXT
);

CREATE TABLE Medicamento (
  id_medicamento INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  nome TEXT NOT NULL
);

CREATE TABLE Med_presc (
  id_prescricao INTEGER,
  id_medicamento INTEGER,
  FOREIGN KEY (id_prescricao) REFERENCES Prescricao(id_prescricao),
  FOREIGN KEY (id_medicamento) REFERENCES Medicamento(id_medicamento)
);
