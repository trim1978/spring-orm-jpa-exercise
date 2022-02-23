insert into authors (id, `name`) values (1, 'Pushkin');
insert into authors (id, `name`) values (2, 'Lermontov');
insert into authors (id, `name`) values (3, 'Barto');

insert into GENRES (id, name) values (1, 'adventures');
insert into GENRES (id, name) values (2, 'horror');
insert into GENRES (id, name) values (3, 'fantasy');
insert into GENRES (id, name) values (4, 'lyric');
insert into GENRES (id, name) values (5, 'thriller');
insert into GENRES (id, name) values (6, 'drama');
insert into GENRES (id, name) values (7, 'comedy');

insert into Books (id, title, author, genre) values (1, 'Metel', 1, 4);
insert into Books (id, title, author, genre) values (2, 'Mciri', 2, 4);

insert into COMMENTS (id, text, book) values (1, 'wow', 2);
