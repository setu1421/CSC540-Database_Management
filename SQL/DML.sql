-- INSERT data into USERS table
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('C0001','1234', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('C0002', '5678', 'C');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('admin', 'admin', 'A');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('Brand01', '7891', 'B');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('Brand02', '2345', 'B');
INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES('Brand03', '7890', 'B');
--select * from users;

-- Insert into admin table 
-- admin table not necessaty
--INSERT INTO ADMIN  VALUES('0001', 'Bob', 'Richard', '9876243233', null, '1901 Gromarn St, Raleigh, NC', 'admin');



-- Insert into brand table
INSERT INTO BRAND(BID, BNAME, ADDRESS, JOINDATE) VALUES ('Brand01', 'Brand X', '503 Rolling Creek Dr Austin, AR', '01-APR-2021');
INSERT INTO BRAND(BID, BNAME, ADDRESS, JOINDATE) VALUES ('Brand02', 'Brand Y', '939 Orange Ave Coronado, CA', '25-MAR-2021');
INSERT INTO BRAND(BID, BNAME, ADDRESS, JOINDATE) VALUES ('Brand03', 'Brand Z', '20 Roszel Rd Princeton, NJ', '08-MAY-2021');
select * from brand;

-- Insert into activity type
INSERT INTO ACTIVITYTYPE(ACTIVITYCODE, ACTIVITYNAME) VALUES ('A01', 'Purchase');
INSERT INTO ACTIVITYTYPE(ACTIVITYCODE, ACTIVITYNAME) VALUES ('A02', 'Write a review');
INSERT INTO ACTIVITYTYPE(ACTIVITYCODE, ACTIVITYNAME) VALUES ('A03', 'Refer a friend');
--select * from ACTIVITY_TYPE;

-- Insert into reward type
INSERT INTO REWARDTYPE(REWARDCODE, REWARDNAME) VALUES ('R01', 'Gift Card');
INSERT INTO REWARDTYPE(REWARDCODE, REWARDNAME) VALUES ('R02', 'Free Product');
--select * from REWARD_TYPE;


-- Insert into RE rules 
INSERT INTO RERULE(VERSIONNO, POINTS, ACTIVITYCODE, BRANDID) VALUES (1, 15, 'A01', 'Brand01');
INSERT INTO RERULE(VERSIONNO, POINTS, ACTIVITYCODE, BRANDID) VALUES (1, 10, 'A02', 'Brand01');
INSERT INTO RERULE(VERSIONNO, POINTS, ACTIVITYCODE, BRANDID) VALUES (1, 40, 'A01', 'Brand02');
INSERT INTO RERULE(VERSIONNO, POINTS, ACTIVITYCODE, BRANDID) VALUES (1, 30, 'A03', 'Brand02');
INSERT INTO RERULE(VERSIONNO, POINTS, ACTIVITYCODE, BRANDID) VALUES (1, 10, 'A03', 'Brand03');

--select * from rerule;


-- Insert into rr rules 
INSERT INTO RRRULE(VERSIONNO, POINTS, REWARDCODE, BRANDID) VALUES (1, 80, 'R01', 'Brand01');
INSERT INTO RRRULE(VERSIONNO, POINTS, REWARDCODE, BRANDID) VALUES (1, 70, 'R02', 'Brand01');
INSERT INTO RRRULE(VERSIONNO, POINTS, REWARDCODE, BRANDID) VALUES (1, 120, 'R01', 'Brand02'); 
INSERT INTO RRRULE(VERSIONNO, POINTS, REWARDCODE, BRANDID) VALUES (1, 90, 'R02', 'Brand02'); 
INSERT INTO RRRULE(VERSIONNO, POINTS, REWARDCODE, BRANDID) VALUES (1, 100, 'R01', 'Brand03'); 
--select* from rrrule;


-- Insert into LOYALTYPROGRAM 
INSERT INTO LOYALTYPROGRAM(LPCODE, LPNAME, LPTYPE, ISVALID, BRANDID) VALUES('TLP01', 'SportGoods', 'T', 0, 'Brand01');
INSERT INTO LOYALTYPROGRAM(LPCODE, LPNAME, LPTYPE, ISVALID, BRANDID) VALUES('TLP02', 'MegaCenter', 'T', 0, 'Brand02');
INSERT INTO LOYALTYPROGRAM(LPCODE, LPNAME, LPTYPE, ISVALID, BRANDID) VALUES('RLP01', 'TechSups', 'R', 0, 'Brand03');

--SELECT * FROM LOYALTYPROGRAM;

-- Insert into TABKEY 
INSERT INTO TABKEY(KEY, ID) VALUES('TLP', 5);
INSERT INTO TABKEY(KEY, ID) VALUES('RLP', 5);


-- INSERT INTO TIER
INSERT INTO TIER(TIERNAME, POINTS, MULTIPLIER, LPCODE) VALUES('BRONZE', 0, 1, 'TLP01');
INSERT INTO TIER(TIERNAME, POINTS, MULTIPLIER, LPCODE) VALUES('SILVER', 170, 2, 'TLP01');
INSERT INTO TIER(TIERNAME, POINTS, MULTIPLIER, LPCODE) VALUES('GOLD', 270, 3, 'TLP01');
INSERT INTO TIER(TIERNAME, POINTS, MULTIPLIER, LPCODE) VALUES('SPECIAL', 0, 1, 'TLP02');
INSERT INTO TIER(TIERNAME, POINTS, MULTIPLIER, LPCODE) VALUES('PREMIUM', 210, 2, 'TLP02');
--SELECT * FROM TIER;


--insert into brand activity type
INSERT INTO BRANDACTIVITYTYPE(BRANDID, ACTIVITYCODE) VALUES('Brand01', 'A01');
INSERT INTO BRANDACTIVITYTYPE(BRANDID, ACTIVITYCODE) VALUES('Brand01', 'A02');
INSERT INTO BRANDACTIVITYTYPE(BRANDID, ACTIVITYCODE) VALUES('Brand02', 'A01');
INSERT INTO BRANDACTIVITYTYPE(BRANDID, ACTIVITYCODE) VALUES('Brand02', 'A03');
INSERT INTO BRANDACTIVITYTYPE(BRANDID, ACTIVITYCODE) VALUES('Brand03', 'A03');

--select * from BRANDACTIVITYTYPE;


--insert into brand REWARD type
INSERT INTO BRANDREWARDTYPE(BRANDID, REWARDCODE, TOTQUANTITY, CURQUANTITY) VALUES('Brand01', 'R01', 40, 38);
INSERT INTO BRANDREWARDTYPE(BRANDID, REWARDCODE, TOTQUANTITY, CURQUANTITY) VALUES('Brand01', 'R02', 25, 23);
INSERT INTO BRANDREWARDTYPE(BRANDID, REWARDCODE, TOTQUANTITY, CURQUANTITY) VALUES('Brand02', 'R01', 30, 28);
INSERT INTO BRANDREWARDTYPE(BRANDID, REWARDCODE, TOTQUANTITY, CURQUANTITY) VALUES('Brand02', 'R02', 50, 47);
INSERT INTO BRANDREWARDTYPE(BRANDID, REWARDCODE, TOTQUANTITY, CURQUANTITY) VALUES('Brand03', 'R01', 25, 25);

--select * from BRANDREWARDTYPE;



-- Insert into customer table
INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS, WALLETID) VALUES('C0001', 'Peter', 'Parker', '8636368778', '20 Ingram Street, NY', 'W0001');
INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS, WALLETID) VALUES('C0002', 'Steve', 'Rogers', '8972468552', '569 Leaman Place, NY', 'W0002');
INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS, WALLETID) VALUES('C0003', 'Diana', 'Prince', '8547963210', '1700 Broadway St, NY', 'W0003');
INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS, WALLETID) VALUES('C0004', 'Billy', 'Batson', '8974562583', '5015 Broad St, Philadelphia, PA', 'W0004');
INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS, WALLETID) VALUES('C0005', 'Tony', 'Stark', '8731596464', '10880 Malibu Point, CA', 'W0005');
--select * from customer;


--INSERT INTO ENROL LP
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED) VALUES('C0001', 'TLP01', 0);
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED) VALUES('C0001', 'TLP02', 0);
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED) VALUES('C0002', 'TLP01', 0);
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED) VALUES('C0003', 'TLP02', 20);
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED) VALUES('C0003', 'RLP01', 40);
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED) VALUES('C0005', 'TLP01', 170);
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED) VALUES('C0005', 'TLP02', 160);
INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED) VALUES('C0005', 'RLP01', 50);
--SELECT * FROM ENROLLP;


--INSERT INTO WALLET FOR REWARD EARNING
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0001', 'Brand01', 'A01', 60, '10-JUN-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0001', 'Brand01', 'A02', 20, '18-JUN-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0001', 'Brand02', 'A01', 80, '09-AUG-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0001', 'Brand02', 'A01', 40, '15-AUG-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0001', 'Brand02', 'A03', 30, '03-OCT-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0001', 'Brand02', 'A03', 60, '18-OCT-2021');

INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0002', 'Brand01', 'A01', 30, '02-JUL-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0002', 'Brand01', 'A01', 30, '08-JUL-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0002', 'Brand01', 'A02', 10, '05-AUG-2021');

INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0003', 'Brand03', 'A03', 40, '30-JUL-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0003', 'Brand02', 'A01', 40, '01-AUG-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0003', 'Brand02', 'A01', 80, '10-AUG-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0003', 'Brand02', 'A01', 20, '02-SEP-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0003', 'Brand02', 'A03', 30, '01-OCT-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0003', 'Brand02', 'A03', 30, '16-OCT-2021');

INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0005', 'Brand01', 'A01', 90, '10-AUG-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0005', 'Brand03', 'A03', 30, '16-SEP-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0005', 'Brand03', 'A03', 20, '30-SEP-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0005', 'Brand01', 'A02', 50, '30-SEP-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0005', 'Brand02', 'A01', 160, '10-OCT-2021');
INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY) VALUES('C0005', 'Brand01', 'A02', 30, '15-OCT-2021');

--SELECT * FROM WALLETRE;


--INSERT INTO WALLET FOR REWARD REDEEMING
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0001', 'Brand01', 'R01', 80, '02-JUL-2021');
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0001', 'Brand02', 'R01', 120, '25-AUG-2021');
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0001', 'Brand02', 'R02', 90, '20-OCT-2021');

INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0002', 'Brand01', 'R02', 70, '01-SEP-2021');

INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0003', 'Brand02', 'R02', 90, '26-AUG-2021');
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0003', 'Brand02', 'R02', 90, '18-OCT-2021');

INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0005', 'Brand02', 'R01', 120, '11-OCT-2021');
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0005', 'Brand01', 'R01', 80, '11-OCT-2021');
INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREDEEMED, DATEOFACTIVITY) VALUES('C0005', 'Brand01', 'R02', 70, '17-OCT-2021');

--SELECT * FROM WALLETRR;


--INSERT INTO REVIEW
INSERT INTO REVIEW(REVIEWTEXT, CUSTOMERID, BID) VALUES('WOW VERY GOOD VERY GOOD VERY GOOD VERY GOOD0000000WOWWWWWWW', 'C0001', 'Brand01');
INSERT INTO REVIEW(REVIEWTEXT, CUSTOMERID, BID) VALUES('EX VERY GOOD VERY GOOD VERY GOOD VERY GOOD0000000', 'C0002', 'Brand01');
INSERT INTO REVIEW(REVIEWTEXT, CUSTOMERID, BID) VALUES('NICE VERY GOOD VERY GOOD VERY GOOD VERY GOOD0000000', 'C0005', 'Brand01');
INSERT INTO REVIEW(REVIEWTEXT, CUSTOMERID, BID) VALUES('VERY GOOD VERY GOOD VERY GOOD VERY GOOD0000000111111111', 'C0005', 'Brand01');
--SELECT * FROM REVIEW;


--INSERT INTO PURCHASE
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0001', 'Brand01', 0);
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0001', 'Brand02', 0);
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0001', 'Brand02', 0);

INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0002', 'Brand01', 0);
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0002', 'Brand01', 0);

INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0003', 'Brand02', 0);
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0003', 'Brand02', 0);
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0003', 'Brand02', 0);

INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0005', 'Brand01', 0);
INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES('C0005', 'Brand02', 0);

--SELECT * FROM PURCHASE;



--INSERT INTO REDEEM POINTS
INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R01', 'C0001', 'Brand01', 1);
INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R01', 'C0001', 'Brand02', 1);
INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R02', 'C0001', 'Brand02', 1);

INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R02', 'C0002', 'Brand01', 1);

INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R02', 'C0003', 'Brand02', 1);
INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R02', 'C0003', 'Brand02', 1);

INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R01', 'C0005', 'Brand02', 1);
INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R01', 'C0001', 'Brand01', 1);
INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES ('R02', 'C0001', 'Brand01', 1);
--SELECT * FROM REDEEM;


--INSERT INTO REFER A friend
INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0001', 'Brand02');
INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0001', 'Brand02');

INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0003', 'Brand03');
INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0003', 'Brand02');
INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0003', 'Brand02'); 

INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0005', 'Brand03');
INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES ('C0005', 'Brand03');
--SELECT * FROM REFERFRIEND;
