INSERT INTO grl_pais(pk_id_pais, nome_pais) VALUES 
	(1,'Angola'),
	(2,'Marrocos'),
	(3,'França'),
	(4,'Portugal'),
	(5,'México'),
	(6,'Canadá'),
	(7,'África do Sul'),
	(8,'Congo'),
	(9,'Namíbia'),
	(10,'Brasil'),
	(11,'Argélia');
	
	
-- Tabela Província

INSERT INTO grl_provincia(nome_provincia, fk_id_pais)  VALUES 
	('Bengo',1),
	('Benguela',1),
	('Bie',1),
	('Cabinda',1),
	('Cunene',1),
	('Kuando-Kubango',1),
	('Kwanza-Norte',1),
	('Kwanza-Sul',1),
	('Huambo',1),
	('Huila',1),
	('Luanda',1),
	('Lunda-Norte',1),
	('Lunda-Sul',1),
	('Malanje',1),
	('Moxico',1),
	('Namibe',1),
	('Uige',1),
	('Zaire',1);

-- Tabela Município

INSERT INTO grl_municipio(nome_municipio, fk_id_provincia) VALUES 
	-- Bengo
	('Ambriz',1),
	('Bula',1),
	('Atumba',1),
	('Dembos',1),
	('Nambuangongo',1),
	('Pango Aluquem',1),

	-- Benguela
	('Baia Farta',2),
	('Balombo',2),
	('Benguela',2),
	('Bocoio',2),
	('Caimbambo',2),
	('Catumbela',2),
	('Chongoroi',2),
	('Cubal',2),
	('Ganda',2),
	('Lobito',2),

	-- Bié
	('Andulo',3),
	('Camacupa',3),
	('Catabola',3),
	('Chinguar',3),
	('Chitembo',3),
	('Cuemba',3),
	('Cunhinga',3),
	('Kuito',3),
	('Nharea',3),

	-- Cabinda
	('Cabinda',4),
	('Cacongo',4),
	('Buco-Zau',4),
	('Belize',4),


	-- Cunene
	('Cahama',5),
	('Cuanhama',5),
	('Curoca',5),
	('Cuvelai',5),
	('Namacunde',5),
	('Ombadja',5),

	-- Kuando-Kubango
	('Calai',6),
	('Cuangar',6),
	('Cuchi',6),
	('Cuito',6),
	('Cuanavale',6),
	('Dirico',6),
	('Mavinga',6),
	('Menongue',6),
	('Nancova',6),
	('Rivungo',6),

	-- Kwanza Norte
	('Ambaca',7),
	('Banga',7),
	('Bolongongo',7),
	('Cambambe',7),
	('Cazengo',7),
	('Golungo Alto',7),
	('Gonguembo',7),
	('Lucala',7),
	('Quiculungo',7),
	('Samba Caju',7),

	-- Kwanza-Sul
	('Amboim',8),
	('Cassongue',8),
	('Cela',8),
	('Conda',8),
	('Ebo',8),
	('Libolo',8),
	('Mussende',8),
	('Porto Amboim',8),
	('Quilenda',8),
	('Quibala',8),
	('Seles',8),
	('Sumbe',8),

	-- Huambo
	('Bailundo',9),
	('Caala',9),
	('Catchiungo',9),
	('Ekunha',9),
	('Huambo',9),
	('Londuimbale',9),
	('Longonjo',9),
	('Mungo',9),
	('Tchicala-Tcholoanga',9),
	('Tchindjenje',9),
	('Ucuma',9),

	-- Huila
	('Caconda',10),
	('Cacula',10),
	('Caluquembe',10),
	('Chiange',10),
	('Chibia',10),
	('Chicomba',10),
	('Chipindo',10),
	('Cuvango',10),
	('Humpata',10),
	('Jamba',10),
	('Lubango',10),
	('Matala',10),
	('Quilengues',10),
	('Quipungo',10),

	-- Luanda
	('Belas',11),
	('Cacuaco',11),
	('Cazenga',11),
	('Icolo e Bengo',11),
	('Luanda',11),
	('Quiçama',11),
	('Viana',11),

	-- Lunda-Norte
	('Cambulo',12),
	('Capenda-Camulemba',12),
	('Caungula',12),
	('Chitato',12),
	('Cuango',12),
	('Cuilo',12),
	('Dundo',12),
	('Lubalo',12),
	('Lucapa Lovua',12),
	('Xá-Muteba',12),

	-- Lunda-Sul
	('Cacolo',13),
	('Dala',13),
	('Muconda',13),
	('Saurimo',13),

	-- Malanje
	('Cacuso',14),
	('Caombo',14),
	('Cambundi-Catembo',14),
	('Cangandala',14),
	('Cuaba Nzogo',14),
	('Cunda-Dia-Baze',14),
	('Kalandula',14),
	('Luquembo',14),
	('Malanje',14),
	('Marimba',14),
	('Massango',14),
	('Mucari',14),
	('Quela',14),
	('Quirima',14),

	-- Moxico
	('Alto Zambeze',15),
	('Bundas',15),
	('Camanongue',15),
	('Léua',15),
	('Luacano',15),
	('Luau',15),
	('Luchazes',15),
	('Lumeje',15),
	('Moxico',15),

	-- Namibe
	('Bibala',16),
	('Camucuio',16),
	('Namibe',16),
	('Tombua',16),
	('Virei',16),

	-- Úige
	('Alto Cauale',17),
	('Ambuíla',17),
	('Bembe',17),
	('Buengas',17),
	('Bungo',17),
	('Damba',17),
	('Maquela do Zombo',17),
	('Macacola',17),
	('Milunga',17),
	('Mucaba',17),
	('Negage',17),
	('Puri',17),
	('Quimbele',17),
	('Quitexe',17),
	('Sanza Pombo',17),
	('Songo',17),
	('Uíge',17),

	-- Zaire
	('MBanza Kongo',18),
	('Soyo',18),
	('NZeto',18),
	('Cuimba',18),
	('Nóqui',18),
	('Tomboco',18);

-- Tabela Comuna
INSERT INTO grl_comuna(pk_id__comuna, descricao_comuna, fk_id_municipio) VALUES 
	
	-- Luanda
	(1,'Cassoneca',96),
	(2,'Catete',96),
	(3,'Cabiri',96),
	(4,'Bom Jesus',96),
	(5,'Caculo Cahango',96),
	(6,'Muxima',98),
	(7,'Demba Chio',98),
	(8,'Caxongo Novo (Quixinge)',98),
	(9,'Mumbondo',98),
	(10,'Cabo Ledo',98),
	(11,'Funda',93),
	(12,'Kikolo',93),
	(13,'Cacuaco',93),
	(14,'Cazenga Popular',95),
	(15,'Hoji ya Henda',95),
	(16,'Tala Hadi',95),
	(17,'Calumbo',99),
	(18,'Viana',99),
	(19,'Zango',99),
	(20,'Camama', 93),
	(21,'Benfica', 93),
	(22,'Barra do Kwanza', 93),
	(23,'Mussulo', 93),
	(24,'Vila do Estoril', 93),
	(25,'Futungo', 93),
	(26,'Ramiro', 93);
	
	
-- Tabela Endereços
INSERT INTO grl_endereco(pk_id_endereco, bairro, rua, numero_casa, fk_id_comuna) VALUES 
	(1,'Talatona', 'Acássias Rubras', 'A2', 21),
	(2,'Estagem', 'Fapa', 'A2', 7),
	(3,'Grafanil-km9', 'Travessa 3', '76', 18),
	(4,'Coelho', 'Ruas das Pedras', '22', 18),
	(5,'Coelho', 'Rua do Pneu', '22', 18),
	(6,'Kilamba-Kiaxi', 'Avenida Pedro de Castro Vandunem Loy', '22', 25),
	(7,'28 de Agosto', 'Sr. Tony', '667', 25);
	
	
-- Tabela Area Interna
INSERT INTO grl_area_interna(descricao_area_interna, codigo_area_interna, observacoes) VALUES 
    ('Ambulatório','AMB','Área Interna do hospital responsável pelas consultas feitas.'),
    ('Internamento','INTER','Área interna do hospital responsável pelos pacientes internados.'),
    ('Diagnósticos','DIAG','Área Interna do Hospital responsável pelos Exames.'),
    ('Emergência','EMG','Área Interna do Hospital responsável pelas consultas emergentes.'),
    ('Farmácia','FARM','Área Interna do Hospital responsável pelos materiais Sanitrios.'),
    ('Superintendência','SUPI','Área Interna do hospital responsável pelas escalas de funcionários como Enfermeiros, Médicos, Farmacêuticos, etc.'),
    ('Recursos Humanos','RH','Área Interna do hospital responsável pelo controlo geral de Funcionários.'),
    ('Morgue','MORG','Área Interna do Hospital Responsável pelos tratamento imediato de pacientes chegados a óbito.');
	
	
-- Tabela Contacto

INSERT INTO grl_contacto(pk_id_contacto, telefone1, telefone2, email1, email2) VALUES 
	(1, '917 485 924', '916 025 301', 'hdp@hospitaldivinaprovidencia.net', null),
	(2, '917 485 924', '916 025 301', 'sjc@hospitaldivinaprovidencia.net', null),
	(3, '917 485 924', '916 025 301', 'st@hospitaldivinaprovidencia.net', null),
	(4, '917 485 924', '916 025 301', 'hdp@hospitaldivinaprovidencia.net', null),
	(5, '917 485 924', '916 025 301', 'hdp@hospitaldivinaprovidencia.net', null);
	
	
-- Tabela Instituição
INSERT INTO grl_instituicao(pk_id_instituicao, codigo_instituicao, descricao, fk_id_contacto,fk_id_endereco) VALUES 
	(1, 'HDP', 'Hospital Divina Providência', 1, 6),
	(2, 'SJC', 'HDP - São João Calábria', 2, 6),
	(3, 'SC', 'HDP - Santa Catarina', 3, 6),
	(4, 'SM', 'HDP - São Marcos', 4, 6),
	(5, 'NSP', 'HDP - Nossa Senhora da Paz', 5, 6);
	
	
-- TAbela Centro Hospitalar
INSERT INTO grl_centro_hospitalar(pk_id_centro, codigo_centro, fk_id_instituicao) VALUES 
	(1, 'SJC', 1),
	(2, 'SC', 1),
	(3, 'SM', 1),
	(4, 'NSP', 1);


-- TAbela Dia da Semana
INSERT INTO grl_dia_semana(pk_id_dia_semana, codigo_dia_semana, descricao) VALUES 
(1, 'SEG','Segunda-Feira'), 
(2, 'TER','Terça-Feira'), 
(3, 'QUA','Quarta-Feira'), 
(4, 'QUI','Quinta-Feira'), 
(5, 'SEX','Sexta-Feira'), 
(6, 'SAB','Sábado-Feira'), 
(7, 'DOM','Domingo-Feira');
	

-- TAbela Sexo
INSERT INTO grl_sexo(pk_id_sexo, codigo_sexo, descricao) VALUES 
	(1, 'F', 'Feminino'),
	(2, 'M', 'Masculino');


-- Tabela Estado Civil
INSERT INTO grl_estado_civil(pk_id_estado_civil, codigo_estado_civil, descricao) VALUES 
	(1, 'S', 'Solteiro(a)'),
	(2, 'C', 'Casado(a)'),
	(3, 'V', 'Viúvo(a)'),
	(4, 'D', 'Divorciado(a)');
	

-- TAbela Estado do Convénio
INSERT INTO grl_estado_convenio(pk_id_estado_convenio, codigo_estado_convenio, descricao) VALUES
	(1, '1', 'Activo'),
	(2, '0', 'Inactivo');


-- Tabela Religião

INSERT INTO grl_religiao(pk_id_religiao, descricao) VALUES
     (1, 'Cristianismo'),
     (2, 'Islamismo'),
     (3, 'Judaísmo'),
     (4, 'Confuccionismo'),
(5, 'Budismo');
