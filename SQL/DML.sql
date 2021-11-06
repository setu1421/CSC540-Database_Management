-- INSERT data into USERS table
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('C0001','1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('C0002', '5678', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('admin', 'admin', 'A');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('Brand03', '7890', 'B');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('Brand02', '2345', 'B');
--select * from users;

-- Insert into admin table 
-- admin table not necessaty
--INSERT INTO ADMIN  VALUES('0001', 'Bob', 'Richard', '9876243233', null, '1901 Gromarn St, Raleigh, NC', 'admin');



-- Insert into brand table
INSERT INTO BRAND(BID, BNAME, ADDRESS, JOINDATE) VALUES ('Brand03', 'Uber Technologies, Inc.', 'San Francisco, CA', '01-JAN-2021');
INSERT INTO BRAND(BID, BNAME, ADDRESS, JOINDATE) VALUES ('Brand02', 'Foodlion, Inc.', 'San Francisco, CA', '01-JAN-2021');
select * from brand;

-- Insert into activity type
INSERT INTO ACTIVITYTYPE(ACTIVITYCODE, ACTIVITYNAME) VALUES ('A01', 'Purchase');
INSERT INTO ACTIVITYTYPE(ACTIVITYCODE, ACTIVITYNAME) VALUES ('A02', 'Leave a review');
INSERT INTO ACTIVITYTYPE(ACTIVITYCODE, ACTIVITYNAME) VALUES ('A03', 'Refer a friend');
--select * from ACTIVITY_TYPE;

-- Insert into reward type
INSERT INTO REWARDTYPE(REWARDCODE, REWARDNAME) VALUES ('R01', 'Gift Card');
INSERT INTO REWARDTYPE(REWARDCODE, REWARDNAME) VALUES ('R02', 'Free Product');
--select * from REWARD_TYPE;


-- Insert into RE rules 
INSERT INTO RERULE(VERSIONNO, POINTS, ACTIVITYCODE, BRANDID) VALUES (1, 50, 'A01', 'Brand03');
INSERT INTO RERULE(VERSIONNO, POINTS, ACTIVITYCODE, BRANDID) VALUES (1, 50, 'A02', 'Brand02');
select* from rerule;


-- Insert into rr rules 
INSERT INTO RRRULE(VERSIONNO, POINTS, REWARDCODE, BRANDID) VALUES (1, 100, 'R01', 20, 'Brand03'); 
INSERT INTO RRRULE(VERSIONNO, POINTS, REWARDCODE, BRANDID) VALUES (1, 500, 'R02', 50, 'Brand02'); 
select* from rrrule;


-- Insert into LOYALTYPROGRAM 
INSERT INTO LOYALTYPROGRAM(LPCODE, LPNAME, LPTYPE, ISVALID, BRANDID) VALUES('RLP02', 'TechSups', 'R', 0, 'Brand03');
INSERT INTO LOYALTYPROGRAM(LPCODE, LPNAME, LPTYPE, ISVALID, BRANDID) VALUES('TLP01', 'SportGoods', 'T', 0, 'Brand02');
SELECT * FROM LOYALTYPROGRAM;

-- Insert into TABKEY 
INSERT INTO TABKEY(KEY, ID) VALUES('TLP', 5);
INSERT INTO TABKEY(KEY, ID) VALUES('RLP', 5);


-- INSERT INTO TIER
INSERT INTO TIER(TIERNAME, POINTS, MULTIPLIER, LPCODE) VALUES('BRONZE', 0, 1, 'TLP01');
INSERT INTO TIER(TIERNAME, POINTS, MULTIPLIER, LPCODE) VALUES('SILVER', 170, 2, 'TLP01');
INSERT INTO TIER(TIERNAME, POINTS, MULTIPLIER, LPCODE) VALUES('GOLD', 270, 3, 'TLP01');
SELECT * FROM TIER;


--insert into brand activity type
INSERT INTO BRANDACTIVITYTYPE(BRANDID, ACTIVITYCODE) VALUES('Brand03', 'A01');
INSERT INTO BRANDACTIVITYTYPE(BRANDID, ACTIVITYCODE) VALUES('Brand02', 'A02');
select * from BRANDACTIVITYTYPE;


--insert into brand REWARD type
INSERT INTO BRANDREWARDTYPE(BRANDID, REWARDCODE, TOTQUANTITY, CURQUANTITY) VALUES('Brand03', 'R01', 25, 25);
INSERT INTO BRANDREWARDTYPE(BRANDID, REWARDCODE, TOTQUANTITY, CURQUANTITY) VALUES('Brand02', 'R02', 50, 50);
select * from BRANDREWARDTYPE;



-- Insert into customer table
INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS, WALLETID) VALUES('C0001', 'Peter', 'Parker', '8636368778', '20 Ingram Street, NY', 'W0001');
INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS, WALLETID) VALUES('C0002', 'Steve', 'Rogers', '8972468552', '569 Leaman Place, NY', 'W0002');
select * from customer;


--INSERT INTO ENROL LP
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED, ENROLDATE) VALUES('C0001', 'RLP02', 200, '01-JAN-2021');
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED, ENROLDATE)VALUES('C0002', 'TLP01', 450, '01-JAN-2021');
SELECT * FROM ENROLLP;


--INSERT INTO WALLET FOR REWARD EARNING
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0001', 'Brand03', 'A01', 50, '01-MAR-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0001', 'Brand02', 'A02', 100, '01-MAR-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0002', 'Brand02', 'A02', 100, '01-MAR-2021');
SELECT * FROM WALLETRE;


--INSERT INTO WALLET FOR REWARD REDEEMING
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0001', 'Brand03', 'R01', 5, '01-JUL-2021');
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0001', 'Brand02', 'R02', 10, '01-JUL-2021');
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0002', 'Brand02', 'R02', 10, '01-JUL-2021');
SELECT * FROM WALLETRR;


--INSERT INTO REVIEW
INSERT INTO REVIEW(REVIEWTEXT, CUSTOMERID, BID) VALUES('VERY GOOD VERY GOOD VERY GOOD VERY GOOD0000000', 'C0001', 'Brand03');
INSERT INTO REVIEW(REVIEWTEXT, CUSTOMERID, BID) VALUES('VERY GOOD VERY GOOD VERY GOOD VERY GOOD0000000', 'C0001', 'Brand02');
SELECT * FROM REVIEW;


--INSERT INTO PURCHASE
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0001', 'Brand03', 0);
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0001', 'Brand02', 1);
SELECT * FROM PURCHASE;



--INSERT INTO REDEEM POINTS
INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('BDJD43', 'C0001', 'Brand03', 2);
INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('HDFE91', 'C0001', 'Brand02', 5);
SELECT * FROM REDEEM;


--INSERT INTO REFER A friend
INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0001', 'Brand03'); 
INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0002', 'Brand02'); 
SELECT * FROM REFERFRIEND;
