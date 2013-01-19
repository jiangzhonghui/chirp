--
-- Chirp initialization script.
--

--
-- Users
--
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int(40) NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  username varchar(50) NOT NULL,
  token varchar(50),
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB;

insert into users(username, first_name, last_name, token) values('fabio.strozzi', 'Fabio', 'Strozzi', 'fc57c1fc-60fd-11e2-8d5b-544249f16afb');
insert into users(username, first_name, last_name, token) values('jack.sparrow', 'Jack', 'Sparrow', '02392a98-60fe-11e2-8d6e-544249f16afb');
insert into users(username, first_name, last_name) values('tiger.man', 'Tiger', 'Man');
insert into users(username, first_name, last_name) values('hulk.hogan', 'Hulk', 'Hogan');
insert into users(username, first_name, last_name, token) values('ip.man', 'Ip', 'Man', '54dd989c-60fe-11e2-a0f3-544249f16afb');

--
-- Followers
--
DROP TABLE IF EXISTS followers;

CREATE TABLE followers (
  id int(40) NOT NULL AUTO_INCREMENT,
  user_id int(40) NOT NULL,
  followed_id int(40) NOT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB;

insert into followers(user_id, followed_id) values((select id from users where username = 'fabio.strozzi'), (select id from users where username='jack.sparrow'));
insert into followers(user_id, followed_id) values((select id from users where username = 'fabio.strozzi'), (select id from users where username='tiger.man'));
insert into followers(user_id, followed_id) values((select id from users where username = 'fabio.strozzi'), (select id from users where username='ip.man'));

insert into followers(user_id, followed_id) values((select id from users where username = 'jack.sparrow'), (select id from users where username='fabio.strozzi'));
insert into followers(user_id, followed_id) values((select id from users where username = 'jack.sparrow'), (select id from users where username='ip.man'));
insert into followers(user_id, followed_id) values((select id from users where username = 'jack.sparrow'), (select id from users where username='tiger.man'));

insert into followers(user_id, followed_id) values((select id from users where username = 'ip.man'), (select id from users where username='hulk.hogan'));
insert into followers(user_id, followed_id) values((select id from users where username = 'ip.man'), (select id from users where username='tiger.man'));

insert into followers(user_id, followed_id) values((select id from users where username = 'tiger.man'), (select id from users where username='hulk.hogan'));


--
-- Chirps
--
DROP TABLE IF EXISTS chirps;

CREATE TABLE chirps (
    id int(40) NOT NULL AUTO_INCREMENT,
    user_id int(40) NOT NULL,
    content varchar(200) NOT NULL,
    created timestamp NOT NULL,
    PRIMARY KEY(id) USING BTREE
) ENGINE=InnoDB;

insert into chirps(user_id, created, content) values((select id from users where username = 'fabio.strozzi'), NOW(), 'My first chirp!');
insert into chirps(user_id, created, content) values((select id from users where username = 'fabio.strozzi'), NOW(), 'My second chirp!');
insert into chirps(user_id, created, content) values((select id from users where username = 'fabio.strozzi'), NOW(), 'I like chirp!');
insert into chirps(user_id, created, content) values((select id from users where username = 'fabio.strozzi'), NOW(), 'Chirp is written in Java!');

COMMIT;
