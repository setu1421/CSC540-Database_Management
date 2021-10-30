-- INSERT data into USERS table
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('admin', 'admin', 'A');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('rickgeller', '1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('mikegeller', '1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('nickmike', '1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('uber', 'uberEAT', 'B');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('foodlion', 'LionFood', 'B');
--select * from users;

-- Insert into admin table
-- admin table not necessaty
--INSERT INTO ADMIN  VALUES('0001', 'Bob', 'Richard', '9876243233', null, '1901 Gromarn St, Raleigh, NC', 'admin');

-- Insert into customer table
INSERT INTO CUSTOMER VALUES('rickgeller', 'Rick', 'Geller', '9841243233', '1901 Gromarn St, Raleigh, NC');
INSERT INTO CUSTOMER VALUES('mikegeller', 'Mike', 'Geller', '9812243661', '1901 Gromarn St, Raleigh, NC');
INSERT INTO CUSTOMER VALUES('nickmike', 'Nick', 'Mike', '9841232233', '1905 Avent Ferry, Raleigh, NC');

--select * from customer;



-- Insert into brand table
INSERT INTO BRAND VALUES ('uber', 'Uber Technologies, Inc.', 'San Francisco, CA', '01-JAN-2021');
INSERT INTO BRAND VALUES ('foodlion', 'Foodlion, Inc.', 'San Francisco, CA', '01-JAN-2021');
--select * from brand;

-- Insert into activity type
INSERT INTO ACTIVITYTYPE VALUES ('ACND12', 'Refer a friend');
INSERT INTO ACTIVITYTYPE VALUES ('ACND14', 'Purchase a product');
--select * from ACTIVITYTYPE;

-- Insert into reward type
INSERT INTO REWARDTYPE VALUES ('BDJD43', 'Gift Card');
INSERT INTO REWARDTYPE VALUES ('HDFE91', 'Free Product');
--select * from REWARDTYPE;


-- Insert into rr rules
INSERT INTO RRRULE VALUES ('DGJD98', '1', '100', 'BDJD43');
INSERT INTO RRRULE VALUES ('D23D98', '1', '500', 'HDFE91');
--select* from rrrule;


-- Insert into RE rules
INSERT INTO RERULE VALUES ('DFNJ87', 1, 50, 'ACND12');
INSERT INTO RERULE VALUES ('D2NJ87', 1, 50, 'ACND14');
--select* from rerule;





