-- Inserção de Dados nas tabelas Gerais --

-- Tabela País

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

	
-- Tabela Endereços
INSERT INTO grl_endereco(bairro, numero_casa, fk_id_municipio) VALUES 
	('Talatona', 'A2', 1),
	('Estagem', 'A2', 7),
	('Grafanil-km9', 'A2', 7),
	('Coelho', '22', 7),
	('28 de Agosto', '667', 1);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	