--drop table if exists record_transaction;
--drop table if exists customer;
--drop table if exists sub_counties;
--drop table if exists merchant_location;
--
--
--create table customer (
--  id int not null AUTO_INCREMENT,
--  created_date datetime(6) default null,
--  modified_date datetime(6) default null,
--  first_name varchar(100) not null,
--  last_name varchar(100) not null,
--  address varchar(250) not null,
--  city varchar(100) not null,
--  PRIMARY KEY ( id )
--);
--
--create table sub_counties (
--    id int not null AUTO_INCREMENT,
--    created_date datetime(6) default null,
--    modified_date datetime(6) default null,
--    name varchar(100) not null,
--    geom geometry not null,
--    PRIMARY KEY ( id )
--);
--
--create table merchant_location (
--    id int not null AUTO_INCREMENT,
--    created_date datetime(6) default null,
--    modified_date datetime(6) default null,
--    name varchar(100) not null,
--    geom geometry not null,
--    PRIMARY KEY ( id )
--);
--
--create table record_transaction (
--  id int not null AUTO_INCREMENT,
--  created_date datetime(6) default null,
--  modified_date datetime(6) default null,
--  customer_id int not null,
--  merchant_id int not null,
--  PRIMARY KEY ( id ),
--  FOREIGN KEY ( customer_id ) REFERENCES customer (id),
--  FOREIGN KEY ( merchant_id ) REFERENCES merchant_location (id)
--
--);

--POSTGRESSQL
drop table if exists record_transaction;
drop table if exists customer;

CREATE TABLE customer (
	id serial PRIMARY KEY,
	created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP NULL,
	first_name VARCHAR ( 100 )  NOT NULL,
	last_name VARCHAR ( 100 ) NOT NULL,
	address VARCHAR ( 250 ) NOT NULL,
    city VARCHAR ( 100 ) NOT NULL
);

CREATE TABLE record_transaction (
	id serial PRIMARY KEY,
	created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP NULL,
	customer_id int  NOT NULL,
	merchant_id int not null,
    FOREIGN KEY ( customer_id ) REFERENCES customer (id)
);

ALTER TABLE record_transaction ADD FOREIGN KEY (merchant_id) REFERENCES merchant_location (id);


