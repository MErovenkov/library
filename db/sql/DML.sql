INSERT INTO authority(id_authority, name)
    VALUES (1, 'ADMIN'),
           (2, 'USER');

INSERT INTO genre(name)
	VALUES  ('Деловая литература'),
			('Детективы и Триллеры'),
			('Документальная литература'),
			('Дом, ремесла, досуг, хобби'),
			('Драматургия'),
			('Искусство, Искусствоведение, Дизайн'),
			('Компьютеры и Интернет'),
			('Литература для детей'),
			('Любовные романы'),
			('Наука, Образование'),
			('Поэзия'),
			('Приключения'),
			('Проза'),
			('Религия, духовность, эзотерика'),
			('Справочная литература'),
			('Техника'),
			('Фантастика'),
			('Юмор'),
			('Здоровье, красота, психология');
	
INSERT INTO author(surname, name, patronymic)
	VALUES  ('Авенариус',   'Вильгельм',    'Петрович'),
			('Агнивцев',    'Николай',      'Яковлевич'),
            ('Анненская',   'Александра',   'Никитична'),
            ('Батюшков',    'Константин',   'Николаевич'),
            ('Белинский',   'Виссарион',    'Григорьевич'),
            ('Бестужев',    'Александр',    'Александрович'),
            ('Веневи́тинов', 'Дмитрий',      'Владимирович'),
            ('Герцен',      'Александр',    'Иванович'),
            ('Гиппиус',     'Зинаида',      'Николаевна'),
            ('Давыдов',     'Денис',        'Васильевич'),
            ('Жемчужников', 'Александр',    'Михайлович'),
            ('Зенкевич',    'Михаил',       'Александрович'),
            ('Карамзин',    'Николай',      'Михайлович'),
            ('Клычков',     'Сергей',       'Антонович'),
            ('Лажечников',  'Иван',         'Иванович'),
            ('Лёвшин',      'Василий',      'Алексеевич'),
            ('Майков',      'Аполлон',      'Николаевич'),
            ('Муравьёв',    'Михаил',       'Никитич'),
            ('Одоевский',   'Александр',    'Иванович'),
            ('Парнок',      'София',        'Яковлевна'),
            ('Есенин',      'Сергей',       'Александрович'),
            ('Ершов',       'Пётр',         'Павлович');

INSERT INTO author_genre(id_author, id_genre)
	VALUES  (1,	     1),
			(1,	     2),
			(2,	     3),
			(2,	     4),
			(3,	     5),
			(4,	     6),
			(5,	     7),
            (6,	     8),
            (7,	     9),
            (8,	     10),
            (9,	     11),
            (10,	 12),
            (11,	 13),
            (12,	 14),
            (13,	 15),
            (14,	 16),
            (15,	 17),
            (16,	 18),
            (17,	 19),
            (18,	 19),
            (19,	 19),
            (20,	 19);

INSERT INTO publisher(address, name)
    VALUES  ('Адыгея Республика',               'Аванта + (издательское объединение)'),
            ('Алтайский Край',                  'Азбука (издательство)'),
            ('Байконур Город',                  'АКВИЛЕГИЯ-М (издательство)'),
            ('Владимирская Область',            'Арка (издательство)'),
            ('Волгоградская Область',           'АСТ (издательская группа)'),
            ('Горьковская Область',             'СТ-ПРЕСС (издательская компания)'),
            ('Дагестан Республика',             'Б.С.Г.-Пресс (издательство)'),
            ('Еврейская Автономная область',    'Белый Город (издательство)'),
            ('Забайкальский Край',              'Вече (издательство)'),
            ('Ивановская Область',              'Вильямс (издательский дом)'),
            ('Ингушетия Республика',            'Вита Нова (издательство)'),
            ('Омская Область',                  'Время (издательство)'),
            ('Санкт-Петербург',                 'Гаятри/Livebook (издательство)'),
            ('Саратовская Область',             'ДЕТГИЗ-Лицей (издательство)'),
            ('Тамбовская Область',              'Детская литература (издательство)'),
            ('Удмуртская Республика',           'Дрофа (издательский дом)'),
            ('Ульяновская Область',             'Захаров (издательство)'),
            ('Хабаровский Край',                'Карапуз (издательский дом)'),
            ('Челябинская Область',             'Линг-Бук (издательство)'),
            ('Ямало-Ненецкий Автономный округ', 'Литера (издательский дом)');

INSERT INTO book(name,		         number_pages, id_author, id_genre, id_publisher, in_stock, short_specification)
	VALUES
        ('День саранчи',                    645,        1,      1,      1,      true,       'dsagweqrtasfasdfaefawfawefasdgasdgwearfaasdf asdf aw fawefasdf asdf'),
        ('Американская трагедия',           875,        2,      2,      2,      true,       'gsadfgwaeraefa sdfasd fsad fasdf asdga sgsad gasd'),
        ('Случай Портного',                 876,        3,      3,      3,      true,       'asdasfsagweg awe aw a wega weg sd gasdfsda fsaddf sad'),
        ('Эпоха невинности',                325,        4,      4,      4,      true,       'asdga ag awe gaw ega sdf sadf sad fsadg sadgsadg '),
        ('Кролик, беги',                    424,        5,      5,      5,      true,       'asdgasdg sadf sdf asdf sadg er twerg sdfg sdf gsdf'),
        ('Ночь',                            785,        6,      6,      6,      true,       ' sdfgsdfg er gasd gsadf asf ewf a dfasdf asdfwqere'),
        ('Сердце тьмы',                     678,        7,      7,      7,      true,       'jfhkyuky ukyukgfhgj t jyfjghj  fgh jrt dfh g rdt hd'),
        ('Паутина Шарлотты',                877,        8,      8,      8,      true,       ' dfghdfghdfghdfg dfg fdjtrjkyu  liul ui lyu  kg jh '),
        ('Иди, вещай с горы',               675,        9,      9,      9,      true,       'fjh yt rty uil vb mn xdbtr hty kyukl kjg hkyuk yujk fg'),
        ('Вся королевская рать',            65,         11,     10,     10,     true,       ' jdf gdf gjfdgj rdt jhdrth dfgh rt5sd hs fdg sdg esr'),
        ('Сыновья и любовники',             454,        10,     11,     1,      true,       'jgfhjk kuil  fgyj dfy hdth d htr jt se gsdf gt rdthdf'),
        ('Сердце – одинокий охотник',       354,        12,     12,     2,      true,       'hsr efs gxc bvbn drt dfb  brdt hdfb xcr grr g xnrd h'),
        ('Я, Клавдий',                      345,        13,     13,     3,      true,       'gsdfh ser thrt ytuk ty  ty uk  gse gdf gds rtdh sg dfh '),
        ('Фиеста',                          734,        11,     14,     4,      true,       'fhdfgh dfj tyj tfkmf kf df se ysdfhrtd hdr hdf ghd rt'),
        ('Когда я умирала',                 235,        1,      16,     5,      true,       'dfghd drt h se gdfg jdt hdgh rt hdfg serg sdfh a'),
        ('Вечный сон',                      345,        2,      1,      7,      true,       'hdfgh drth drth dgh fgh ser gh hrdth rsdtkyuk'),
        ('В поисках утраченного времени',   123,        3,      2,      9,      true,       'dfghd hdrt hdh fg drth dgjk t5ykyu lkdfhgs s ser'),
        ('Золотая тетрадь',                 456,        4,      3,      16,     true,       'dfgjdf ghdh dtr hdf ghdh rtg sag dfg tr5h dh df'),
        ('Хладнокровное убийство',          976,        5,      4,      16,     true,       'dfgh df ghdf gjtyk fg df rtyh hdf ghdrth dghdf gh'),
        ('Повелитель мух',                  876,        16,     5,      2,      true,       'hdf ghdfg hdf ghdfj drt fgh dfgh  hr dh gdhdrth df'),
        ('Скотный двор',                    857,        17,     6,      4,      true,       'df ghdf ghrtdyd fghd fgh kjyudfh drt hd ghtrdh df');
