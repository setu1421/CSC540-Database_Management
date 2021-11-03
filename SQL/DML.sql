-- INSERT data into USERS table
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('C0001','1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('C0002', '5678', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('admin', 'admin', 'A');
--select * from users;

-- Insert into admin table 
-- admin table not necessaty
--INSERT INTO ADMIN  VALUES('0001', 'Bob', 'Richard', '9876243233', null, '1901 Gromarn St, Raleigh, NC', 'admin');

-- Insert into customer table
INSERT INTO CUSTOMER VALUES('C0001', 'Peter', 'Parker', '8636368778', '20 Ingram Street, NY', );
INSERT INTO CUSTOMER VALUES('C0002', 'Steve', 'Rogers', '8972468552', '569 Leaman Place, NY', );

select * from customer;



-- Insert into brand table
INSERT INTO BRANDS VALUES ('uber', 'Uber Technologies, Inc.', 'San Francisco, CA', '01-JAN-2021');
INSERT INTO BRANDS VALUES ('foodlion', 'Foodlion, Inc.', 'San Francisco, CA', '01-JAN-2021');
select * from brands;

-- Insert into activity type
INSERT INTO ACTIVITYTYPE VALUES ('A01', 'Purchase');
INSERT INTO ACTIVITYTYPE VALUES ('A02', 'Leave a review');
INSERT INTO ACTIVITYTYPE VALUES ('A03', 'Refer a friend');
--select * from ACTIVITY_TYPE;

-- Insert into reward type
INSERT INTO REWARDTYPE VALUES ('R01', 'Gift Card');
INSERT INTO REWARDTYPE VALUES ('R02', 'Free Product');
--select * from REWARD_TYPE;


-- Insert into rr rules 
INSERT INTO RRRULES VALUES ('DGJD98', '1', '100', 'BDJD43'); 
INSERT INTO RRRULES VALUES ('D23D98', '1', '500', 'HDFE91'); 
--select* from rrrules;


-- Insert into RE rules 
INSERT INTO RERULES VALUES ('DFNJ87', 1, 50, 'ACND12');
INSERT INTO RERULES VALUES ('D 2NJ87', 1, 50, 'ACND14');
--select* from rerules;


-- Insert into LOYALTYPROGRAM 
INSERT INTO LOYALTYPROGRAM(LPCODE, LPNAME, LPTYPE, ISVALID, BRANDID) VALUES('RLP02', 'TechSups', 'R', 0, 'Brand03');

-- Insert into TABKEY 
INSERT INTO TABKEY(KEY, ID) VALUES('TLP', 5);
INSERT INTO TABKEY(KEY, ID) VALUES('RLP', 5);
