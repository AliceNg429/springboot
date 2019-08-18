DROP TABLE IF EXISTS person;

CREATE TABLE person (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(128),
  age INTEGER(3),
  address VARCHAR(128)
);

insert into person(name,age,address) values('汪云飞',32,'合肥');
insert into person(name,age,address) values('xx',31,'北京');
insert into person(name,age,address) values('',30,'上海');
insert into person(name,age,address) values('',29,'南京');
insert into person(name,age,address) values('',28,'武汉');
insert into person(name,age,address) values('',27,'合肥');