CREATE TABLE USERS (ID INT PRIMARY KEY AUTO_INCREMENT,
LOGIN VARCHAR(30),
PASSWORD VARCHAR(30),
FIRST_NAME VARCHAR(30),
LAST_NAME VARCHAR(30),
BIRTHDAY DATE,
REGISTER DATE,
STATUS BOOL,
ROLE VARCHAR(255));

CREATE TABLE EDITIONS (ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
FOREIGN KEY(USERS_ID) REFERENCES USERS(ID),
TITLE VARCHAR(80),
USERS_ID INT,
INDEX INT,
PUBLISHING_HOUSE VARCHAR(100),
RECOMMENDED_PRICE INT NOT NULL);

CREATE TABLE SUBSCRIPTIONS(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
FOREIGN KEY(USERS_ID) REFERENCES USERS(ID),
FOREIGN KEY(EDITIONS_ID) REFERENCES EDITIONS(ID),
USERS_ID INT,
EDITIONS_ID INT,
ACTUAL_PRICE INT NOT NULL,
QUANTITY INT,
START_DATE DATE,
FINISH_DATE DATE,
SUBSCRIPTION_DATE DATE);

CREATE TABLE PAYMENTS(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
USERS_ID INT,
SUBSCRIPTIONS_ID INT,
FOREIGN KEY(USERS_ID) REFERENCES USERS(ID),
FOREIGN KEY(SUBSCRIPTIONS_ID) REFERENCES SUBSCRIPTIONS(ID),
TOTAL_AMOUNT INT,
PAYMENT_STATUS BOOL,
CARD_NUMBER INT NOT NULL);