-- INSERT data into USERS table
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('admin', 'admin', 'A');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('rickgeller', '1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('mikegeller', '1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('nickmike', '1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('uber', 'uberEAT', 'B');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('foodlion', 'LionFood', 'B');

-- Insert into admin table 
INSERT INTO ADMIN  VALUES('0001', 'Bob', 'Richard', '9876243233', null, '1901 Gromarn St, Raleigh, NC', 'admin');

-- Insert into customer table
INSERT INTO CUSTOMER VALUES('1234', 'Rick', 'Geller', '9841243233', null, '1901 Gromarn St, Raleigh, NC', 'rickgeller');
INSERT INTO CUSTOMER VALUES('9087', 'Mike', 'Geller', '9812243661', null, '1901 Gromarn St, Raleigh, NC', 'mikegeller');
INSERT INTO CUSTOMER VALUES('1237', 'Nick', 'Mike', '9841232233', null, '1905 Avent Ferry, Raleigh, NC', 'nickmike');

-- Insert into brand table
INSERT INTO BRANDS VALUES ('5498', 'Uber Technologies, Inc.', 'San Francisco, CA', '01-JAN-2021', 'uber');
INSERT INTO BRANDS VALUES ('9900', 'Foodlion, Inc.', 'San Francisco, CA', '01-JAN-2021', 'foodlion');

-- Insert into activity type
INSERT INTO ACTIVITY_TYPE VALUES ('ACND12', 'Refer a friend');
INSERT INTO ACTIVITY_TYPE VALUES ('ACND14', 'Purchase a product');

-- Insert into reward type
INSERT INTO REWARD_TYPE VALUES ('BDJD43', 'Gift Card');
INSERT INTO REWARD_TYPE VALUES ('HDFE91', 'Free Product');

-- Insert into rr rules 
INSERT INTO RRRULES VALUES ('DGJD98', '1', '100', 'BDJD43'); 
INSERT INTO RRRULES VALUES ('D23D98', '1', '500', 'HDFE91'); 


-- Insert into RE rules 
INSERT INTO RERULES VALUES ('DFNJ87', 1, 50, 'ACND12');
INSERT INTO RERULES VALUES ('D2NJ87', 1, 50, 'ACND14');





select * from users;
select * from brands;