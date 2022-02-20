-- Database
CREATE DATABASE mydata;
\c mydata;

-- id and Timestamp
CREATE TABLE id_and_timestamp (
    id serial NOT NULL,
    ourtimestamp DATE NOT NULL
);

-- insert timestamp
INSERT INTO id_and_timestamp (ourtimestamp) VALUES ('2016-09-11');
INSERT INTO id_and_timestamp (ourtimestamp) VALUES ('2016-09-12');
INSERT INTO id_and_timestamp (ourtimestamp) VALUES ('2016-09-13');
INSERT INTO id_and_timestamp (ourtimestamp) VALUES ('2016-09-14');
INSERT INTO id_and_timestamp (ourtimestamp) VALUES ('2016-09-09');
INSERT INTO id_and_timestamp (ourtimestamp) VALUES ('2016-09-08');
INSERT INTO id_and_timestamp (ourtimestamp) VALUES ('2016-09-15');
COMMIT;

-- operators
CREATE TABLE operators(
    id integer NOT NULL,
    PRIMARY KEY (id),
    login VARCHAR (255),
    full_name VARCHAR (255)
);

-- calls
CREATE TABLE calls(
    id integer NOT NULL PRIMARY KEY,
    dates DATE NOT NULL,
    duration INTERVAL NOT NULL,
    direction VARCHAR(3)
);

ALTER TABLE calls ADD operator_id INT REFERENCES operators(id);


INSERT INTO operators (id,login,full_name) VALUES (7343, 'petrov_pv', 'Петров Павел Владимирович');
INSERT INTO operators (id,login,full_name) VALUES (7346, 'antonyan_aa', 'Антонян Левон Алексеевич');
INSERT INTO operators (id,login,full_name) VALUES (7348, 'lavanda_lv', 'Лаванда Любовь Валентиновна');
INSERT INTO operators (id,login,full_name) VALUES (7349, 'alpaka_ok', 'Альпака Олег Константинович');
COMMIT;

INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (18934874, '2022-01-02', 7348, '0:02:12', 'IN');
INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (28478348, '2022-01-03', 7349, '0:02:04', 'IN');
INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (28583984, '2022-01-04', 7346, '0:01:36', 'OUT');
INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (28649893, '2022-01-04', 7343, '0:02:44', 'OUT');
INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (38934874, '2022-01-05', 7343, '0:01:15', 'IN');
INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (48478348, '2022-01-05', 7346, '0:03:42', 'OUT');
INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (48583984, '2022-01-05', 7346, '0:01:56', 'IN');
INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (48649893, '2022-01-05', 7343, '0:05:23', 'IN');
INSERT INTO calls (id,dates,operator_id,duration,direction) VALUES (58934874, '2022-01-05', 7346, '0:06:31', 'OUT');
COMMIT;