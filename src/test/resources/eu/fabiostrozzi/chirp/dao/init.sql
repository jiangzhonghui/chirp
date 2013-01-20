--
-- Chirp initialization script.
--

--
-- Users
--
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  user_id int(40) NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  username varchar(50) NOT NULL,
  salt varchar(10) NOT NULL,
  hash varchar(50),
  PRIMARY KEY (user_id) USING BTREE
) ENGINE=InnoDB;

-- token will be 1#fc57c1fc-60fd-11e2-8d5b-544249f16afb
insert into users(username, first_name, last_name, salt, hash) values('fabio.strozzi', 'Fabio', 'Strozzi', '15264', '309b0f0dce80ec2a1e7dbb3e4a0d38d3');
-- token will be 2#02392a98-60fe-11e2-8d6e-544249f16afb
insert into users(username, first_name, last_name, salt, hash) values('jack.sparrow', 'Jack', 'Sparrow', '26764', 'e0442a61f9d7659fd02522f1d5dc4daf');
insert into users(username, first_name, last_name, salt, hash) values('tiger.man', 'Tiger', 'Man', '1960', NULL);
insert into users(username, first_name, last_name, salt, hash) values('hulk.hogan', 'Hulk', 'Hogan', '25776', NULL);
-- token will be 5#54dd989c-60fe-11e2-a0f3-544249f16afb
insert into users(username, first_name, last_name, salt, hash) values('ip.man', 'Ip', 'Man', '4500', '6121da18caf1bdf04399b4c4d4ff8088');

--
-- Followers
--
DROP TABLE IF EXISTS followers;

CREATE TABLE followers (
  id int(40) NOT NULL AUTO_INCREMENT,
  following_id int(40) NOT NULL,
  followed_id int(40) NOT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB;

insert into followers(following_id, followed_id) values((select user_id from users where username = 'fabio.strozzi'), (select user_id from users where username='jack.sparrow'));
insert into followers(following_id, followed_id) values((select user_id from users where username = 'fabio.strozzi'), (select user_id from users where username='tiger.man'));
insert into followers(following_id, followed_id) values((select user_id from users where username = 'fabio.strozzi'), (select user_id from users where username='ip.man'));

insert into followers(following_id, followed_id) values((select user_id from users where username = 'jack.sparrow'), (select user_id from users where username='fabio.strozzi'));
insert into followers(following_id, followed_id) values((select user_id from users where username = 'jack.sparrow'), (select user_id from users where username='ip.man'));
insert into followers(following_id, followed_id) values((select user_id from users where username = 'jack.sparrow'), (select user_id from users where username='tiger.man'));

insert into followers(following_id, followed_id) values((select user_id from users where username = 'ip.man'), (select user_id from users where username='hulk.hogan'));
insert into followers(following_id, followed_id) values((select user_id from users where username = 'ip.man'), (select user_id from users where username='tiger.man'));

insert into followers(following_id, followed_id) values((select user_id from users where username = 'tiger.man'), (select user_id from users where username='hulk.hogan'));


--
-- Chirps
--
DROP TABLE IF EXISTS chirps;

CREATE TABLE chirps (
    chirp_id int(40) NOT NULL AUTO_INCREMENT,
    user_id int(40) NOT NULL,
    content varchar(200) NOT NULL,
    created timestamp NOT NULL,
    PRIMARY KEY(chirp_id) USING BTREE
) ENGINE=InnoDB;

insert into chirps(user_id, created, content) values((select user_id from users where username = 'fabio.strozzi'), TIMESTAMP('2013-01-01 10:00:00'), 'My first chirp!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'fabio.strozzi'), TIMESTAMP('2013-01-02 09:00:00'), 'My second chirp!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'fabio.strozzi'), TIMESTAMP('2013-01-03 12:00:00'), 'I like chirp!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'fabio.strozzi'), TIMESTAMP('2013-01-04 18:00:00'), 'Chirp is written in Java!');

insert into chirps(user_id, created, content) values((select user_id from users where username = 'jack.sparrow'), TIMESTAMP('2013-01-01 13:00:00'), 'Bought a new boat!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'jack.sparrow'), TIMESTAMP('2013-01-04 14:00:00'), 'I am a pirate!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'jack.sparrow'), TIMESTAMP('2013-01-07 15:00:00'), 'I like sailing!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'jack.sparrow'), TIMESTAMP('2013-01-10 16:00:00'), 'Row row row your boat!');

insert into chirps(user_id, created, content) values((select user_id from users where username = 'tiger.man'), TIMESTAMP('2013-01-02 22:00:00'), 'I am a tiger!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'tiger.man'), TIMESTAMP('2013-01-05 23:00:00'), 'I like Chirp!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'tiger.man'), TIMESTAMP('2013-01-13 01:00:00'), 'Chirp chirp!');

insert into chirps(user_id, created, content) values((select user_id from users where username = 'hulk.hogan'), TIMESTAMP('2013-01-10 16:00:00'), 'I will beat you');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'hulk.hogan'), TIMESTAMP('2013-01-11 16:00:00'), 'See you on ring!');

insert into chirps(user_id, created, content) values((select user_id from users where username = 'ip.man'), TIMESTAMP('2013-01-10 16:00:00'), 'I am the master!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'ip.man'), TIMESTAMP('2013-01-11 16:00:00'), 'I am the master!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'ip.man'), TIMESTAMP('2013-01-12 16:00:00'), 'I am the master!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'ip.man'), TIMESTAMP('2013-01-13 16:00:00'), 'I am the master!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'ip.man'), TIMESTAMP('2013-01-14 16:00:00'), 'I am the master!');
insert into chirps(user_id, created, content) values((select user_id from users where username = 'ip.man'), TIMESTAMP('2013-01-15 16:00:00'), 'I am the master!');

COMMIT;
