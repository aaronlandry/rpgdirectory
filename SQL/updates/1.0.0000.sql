CREATE TABLE Users (
    id NUMBER(38) CONSTRAINT USER_NN_ID NOT NULL,
    username VARCHAR(30) CONSTRAINT USER_NN_USERNAME NOT NULL,
    creationDate timestamp DEFAULT sysdate CONSTRAINT USER_NN_CREATIONDATE NOT NULL,
    firstName varchar(100) CONSTRAINT USER_NN_FIRSTNAME NOT NULL,
    lastName VARCHAR(100) CONSTRAINT USER_NN_LASTNAME NOT NULL,
    middleName VARCHAR(50) CONSTRAINT USER_NN_MIDDLENAME NOT NULL,
    password VARCHAR(255) DEFAULT 'JOOKIE' CONSTRAINT USER_NN_PASSWORD NOT NULL,
    roleID NUMBER(2) DEFAULT 1 CONSTRAINT USER_NN_ROLE NOT NULL,
    CONSTRAINT USER_PKEY PRIMARY KEY (id) USING INDEX (CREATE INDEX USER_IDX_PKEY on Users(id))
) TABLESPACE RPG_DATA;

CREATE INDEX USER_IDX_USER ON Users (username);
CREATE INDEX USER_IDX_NAME ON Users (firstName,lastName);
CREATE INDEX USER_IDX_E_NAME ON Users (lastName,firstName);

CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1 CACHE 100; 

/* NOTE: NEED TO UPDATE PASSWORD WITH ENCRYPTED VALUE */
INSERT INTO Users (id,username,firstName,lastName,middleName,roleID) VALUES (user_seq.nextVal,'alandry','Aaron','Landry','Leigh',2);

CREATE TABLE Games (
    id NUMBER(38) CONSTRAINT GAME_NN_ID NOT NULL,
    userID NUMBER(38) CONSTRAINT GAME_NN_USER NOT NULL,
    creationDate timestamp DEFAULT sysdate CONSTRAINT GAME_NN_CREATIONDATE NOT NULL,
    name varchar(255) CONSTRAINT GAME_NN_NAME NOT NULL,
    categoryID NUMBER(5) DEFAULT 1 CONSTRAINT GAME_NN_CATEGORY NOT NULL,
    CONSTRAINT GAME_PKEY PRIMARY KEY (id) USING INDEX (CREATE INDEX GAME_IDX_PKEY on Games(id)),
    CONSTRAINT GAME_FKEY_USER FOREIGN KEY(userID) REFERENCES Users(id)
) TABLESPACE RPG_DATA;

CREATE INDEX GAME_IDX_USER ON Games (userID);
CREATE INDEX GAME_IDX_NAME ON Games (name);
CREATE INDEX GAME_IDX_E_CDATE ON Games (creationDate);
CREATE INDEX GAME_IDX_E_CAT ON Games (categoryID);

CREATE SEQUENCE games_seq START WITH 1 INCREMENT BY 1 CACHE 100;