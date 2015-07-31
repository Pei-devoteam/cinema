create table movie (
	id int not null auto_increment,
	name varchar(200) not null,
	release_date date not null,
	description text not null,
	image blob,
	trailer_url varchar(200),
	version int not null default 0,
	primary key(id)
);

create table actor (
	id int not null auto_increment,
	name varchar2(200) not null,
	primary key(id)
);

create table movie_actor(
	movie_id int not null,
	actor_id int not null,
	primary key(movie_id, actor_id),
	constraint fk_movie_actor_1 foreign key (movie_id) references movie(id) on delete cascade,
	constraint fk_movie_actor_2 foreign key (actor_id) references actor(id) on delete cascade
);

CREATE TABLE users
(
  id int NOT NULL auto_increment primary key,
  username character varying(60) NOT NULL,
  password character varying(20) NOT NULL,
  role character varying(20) NOT NULL,
  birth_date date,
  country character varying(60),
  enabled boolean NOT NULL,
  verification_code character varying(200),
  created_date timestamp,
  credit_card_id int
);

CREATE TABLE credit_cards (
   id int NOT NULL auto_increment,
   issuer character varying(20) NOT NULL,
   card_number character varying(20) NOT NULL
);

CREATE TABLE ratings (
	id int NOT NULL auto_increment,
	rating int NOT NULL,
	movie_id int NOT NULL,
	user_id int NOT NULL
);

insert into credit_cards(issuer,card_number) values ('VISA','xxxxxxxx');
insert into users(username,password,role,credit_card_id,enabled) values ('almir.pehratovic@gmail.com','user','ROLE_USER',1,true);
insert into users(username,password,role,credit_card_id,enabled) values ('admin','admin','ROLE_ADMIN',null,true);

insert into movie(name,release_date,description,image,trailer_url) values ('Jurassic World','2015-07-10','A new theme park is built on the original site of Jurassic Park. Everything is going well until the park''s newest attraction--a genetically modified giant stealth killing machine--escapes containment and goes on a killing spree.',file_read('src/main/resources/database/images/jurassic.jpg'),'https://www.youtube.com/watch?v=RFinNxS5KN4');
insert into movie(name,release_date,description,image,trailer_url) values ('Fight Club','2015-07-10',' An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more...',file_read('src/main/resources/database/images/fight.jpeg'),'https://www.youtube.com/watch?v=SUXWAEX2jlg');
insert into movie(name,release_date,description,image,trailer_url) values ('Minions','2015-07-02','Minions Stuart, Kevin and Bob are recruited by Scarlet Overkill, a super-villain who, alongside her inventor husband Herb, hatches a plot to take over the world.',file_read('src/main/resources/database/images/minions.jpeg'),'https://www.youtube.com/watch?v=eisKxhjBnZ0');
insert into movie(name,release_date,description,image,trailer_url) values ('Home','2015-02-02','When Oh, a loveable misfit from another planet, lands on Earth and finds himself on the run from his own people, he forms an unlikely friendship with an adventurous girl named Tip who is on a quest of her own. Through a series of comic adventures with Tip, Oh comes to understand that being different and making mistakes is all part of being human. And while he changes her planet and she changes his world, they discover the true meaning of the word HOME. ',file_read('src/main/resources/database/images/home.jpg'),'https://www.youtube.com/watch?v=iLGDJkhYnVc');
insert into movie(name,release_date,description,image,trailer_url) values ('Once Upon a Time in America','1984-09-23','A former Prohibition-era Jewish gangster returns to the Lower East Side of Manhattan over thirty years later, where he once again must confront the ghosts and regrets of his old life.',file_read('src/main/resources/database/images/once.jpg'),'https://www.youtube.com/watch?v=mzhX2PD6Srw');
insert into movie(name,release_date,description,image,trailer_url) values ('Taken 3','2015-01-21','Ex-government operative Bryan Mills is accused of a ruthless murder he never committed or witnessed. As he is tracked and pursued, Mills brings out his particular set of skills to find the true killer and clear his name.',file_read('src/main/resources/database/images/taken.jpg'),'https://www.youtube.com/watch?v=HUJZjUaNzgw');
insert into movie(name,release_date,description,image,trailer_url) values ('X-Men: Days of Future Past','2014-05-22','The X-Men send Wolverine to the past in a desperate effort to change history and prevent an event that results in doom for both humans and mutants.',file_read('src/main/resources/database/images/x-men.jpg'),'https://www.youtube.com/watch?v=pK2zYHWDZKo');
insert into movie(name,release_date,description,image,trailer_url) values ('Fifty Shades of Grey','2015-02-13','Literature student Anastasia Steele''s life changes forever when she meets handsome, yet tormented, billionaire Christian Grey.',file_read('src/main/resources/database/images/50.jpg'),'https://www.youtube.com/watch?v=CQERFnGvi_A');
insert into movie(name,release_date,description,image,trailer_url) values ('Jupiter Ascending','2015-02-06','A young woman discovers her destiny as an heiress of intergalactic nobility and must fight to protect the inhabitants of Earth from an ancient and destructive industry.',file_read('src/main/resources/database/images/jupiter.jpg'),'https://www.youtube.com/watch?v=t4ZzMkDLjWI');
insert into movie(name,release_date,description,image,trailer_url) values ('Braveheart','1995-05-24','When his secret bride is executed for assaulting an English soldier who tried to rape her, William Wallace begins a revolt and leads Scottish warriors against the cruel English tyrant who rules Scotland with an iron fist.',file_read('src/main/resources/database/images/brave.jpg'),'https://www.youtube.com/watch?v=wj0I8xVTV18');

insert into actor(name) values ('Chris Pratt');
insert into actor(name) values ('Brad Pitt');
insert into actor(name) values ('Edward Norton');
insert into actor(name) values ('Sandra Bullock');
insert into actor(name) values ('Steve Carell');
insert into actor(name) values ('Jim Parsons');
insert into actor(name) values ('Rihanna');

insert into movie_actor(movie_id,actor_id) values (1,1);
insert into movie_actor(movie_id,actor_id) values (2,2);
insert into movie_actor(movie_id,actor_id) values (2,3);
insert into movie_actor(movie_id,actor_id) values (3,4);
insert into movie_actor(movie_id,actor_id) values (3,5);
insert into movie_actor(movie_id,actor_id) values (3,1);
insert into movie_actor(movie_id,actor_id) values (4,6);
insert into movie_actor(movie_id,actor_id) values (4,7);

insert into ratings(rating,movie_id,user_id) values(2,1,1);