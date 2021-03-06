CREATE TABLE USERS(
    USERID VARCHAR2(50),
    PASSWORD VARCHAR2(30) NOT NULL,
    USERTYPE CHAR(1) NOT NULL,
    CONSTRAINT pk_users primary key(USERID),
	CONSTRAINT CON_USER CHECK(USERTYPE = 'C' OR USERTYPE = 'B' OR USERTYPE = 'A')
);
/


CREATE TABLE BRAND(
	BID VARCHAR2(50),
	BNAME VARCHAR(80) NOT NULL,
	ADDRESS VARCHAR(80),
	JOINDATE DATE NOT NULL,
	CONSTRAINT PK_BRAND primary key(BID),
	CONSTRAINT FK_BRAND FOREIGN KEY (BID) REFERENCES Users(USERID) ON DELETE CASCADE
);
/



CREATE TABLE ACTIVITYTYPE(
	ACTIVITYCODE VARCHAR2(10),
	ACTIVITYNAME VARCHAR2(50) NOT NULL,
	CONSTRAINT pk_activity primary key(ACTIVITYCODE)
);
/



CREATE TABLE REWARDTYPE(
	REWARDCODE VARCHAR2(10),
	REWARDNAME VARCHAR2(50) NOT NULL,
	CONSTRAINT pk_rewards primary key(REWARDCODE)
);
/

CREATE TABLE RERULE(
	VERSIONNO NUMBER(5),
	POINTS NUMBER(10),
	ACTIVITYCODE VARCHAR2(10),
	BRANDID VARCHAR2(50),
	CONSTRAINT PK_RERULE primary key(BRANDID, ACTIVITYCODE, VERSIONNO),
	CONSTRAINT FK_BCODE_RE FOREIGN KEY (BRANDID) REFERENCES BRAND(BID) ON DELETE CASCADE,
	CONSTRAINT FK_ACTIVITYCODE_RE FOREIGN KEY (ACTIVITYCODE) REFERENCES ACTIVITYTYPE(ACTIVITYCODE) ON DELETE CASCADE
);
/


CREATE TABLE RRRULE(
	VERSIONNO NUMBER(5),
	POINTS NUMBER(10),
	REWARDCODE VARCHAR2(10),
	BRANDID VARCHAR2(50),
	CONSTRAINT PK_RRRULE primary key(BRANDID, REWARDCODE, VERSIONNO),
	CONSTRAINT FK_RRCODE FOREIGN KEY (REWARDCODE) REFERENCES REWARDTYPE(REWARDCODE) ON DELETE CASCADE,
	CONSTRAINT FK_BCODE_RR FOREIGN KEY (BRANDID) REFERENCES BRAND(BID) ON DELETE CASCADE
);
/



CREATE TABLE LOYALTYPROGRAM(
	LPCODE VARCHAR2(50),
	LPNAME VARCHAR2(80),
    LPTYPE VARCHAR(1),
	ISVALID NUMBER(1) DEFAULT 0,
	BRANDID VARCHAR2(50),
	CONSTRAINT PK_LP PRIMARY KEY(LPCODE),
	CONSTRAINT FK_LP FOREIGN KEY (BRANDID) REFERENCES BRAND(BID) ON DELETE CASCADE,
	CONSTRAINT CON_LP_VAL CHECK (ISVALID = 0 OR ISVALID = 1),
	CONSTRAINT CON_LP CHECK (LPTYPE = 'R' OR LPTYPE = 'T')
);
/


CREATE TABLE TABKEY(
	KEY VARCHAR2(10),
	ID NUMBER(10),
	CONSTRAINT PK_TABKEY primary key(KEY)
);
/


CREATE TABLE TIER(
	TIERNAME VARCHAR2(20),
	POINTS NUMBER(5),
	MULTIPLIER NUMBER(5),
	LPCODE VARCHAR2(50),
	CONSTRAINT FK_LP21 FOREIGN KEY (LPCODE) REFERENCES LOYALTYPROGRAM(LPCODE),
	CONSTRAINT PK_TIER1 PRIMARY KEY(LPCODE, TIERNAME)
);
/


CREATE TABLE BRANDACTIVITYTYPE(
    BRANDID VARCHAR2(50),
	ACTIVITYCODE VARCHAR2(10),
	CONSTRAINT PK_BRANDACTY primary key(BRANDID, ACTIVITYCODE),
    CONSTRAINT FK_BRAND_BRANDACTY FOREIGN KEY (BRANDID) REFERENCES BRAND(BID) ON DELETE CASCADE,
	CONSTRAINT FK_ACTIVITYCODE_BRANDACTY FOREIGN KEY (ACTIVITYCODE) REFERENCES ACTIVITYTYPE(ACTIVITYCODE) ON DELETE CASCADE
);
/

CREATE TABLE BRANDREWARDTYPE(
    BRANDID VARCHAR2(50),
	REWARDCODE VARCHAR2(10),
    TOTQUANTITY NUMBER(10),
    CURQUANTITY NUMBER(10),
	CONSTRAINT PK_BRANDRETY primary key(BRANDID, REWARDCODE),
    CONSTRAINT FK_BRAND_BRANDRETY FOREIGN KEY (BRANDID) REFERENCES BRAND(BID) ON DELETE CASCADE,
	CONSTRAINT FK_REWARDCODE_BRANDRETY FOREIGN KEY (REWARDCODE) REFERENCES REWARDTYPE(REWARDCODE) ON DELETE CASCADE
);
/


CREATE TABLE CUSTOMER(
	CUSTOMERID VARCHAR2(50),
	FNAME VARCHAR2(50) NOT NULL,
	LNAME VARCHAR2(50),
	PHONENUMBER VARCHAR2(10),
	ADDRESS VARCHAR2(80),
    WALLETID VARCHAR2(10),
	CONSTRAINT pk_customer primary key(CUSTOMERID),
    CONSTRAINT FK_CUSTOMER FOREIGN KEY(CUSTOMERID) REFERENCES USERS(USERID) ON DELETE CASCADE
);
/


CREATE TABLE ENROLLP(
	CUSTOMERID VARCHAR2(50),
	LPCODE VARCHAR2(50),
	POINTSEARNED NUMBER(20),
    ENROLDATE DATE,
	CONSTRAINT PK_ENROL PRIMARY KEY(CUSTOMERID, LPCODE),
	CONSTRAINT FK_ENROL1 FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID) ON DELETE CASCADE,
	CONSTRAINT FK_ENROL2 FOREIGN KEY (LPCODE) REFERENCES LOYALTYPROGRAM(LPCODE) ON DELETE CASCADE
);
/


CREATE TABLE WALLETRE(
    SER NUMBER(10) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	CUSTOMERID VARCHAR2(50),
	BID VARCHAR2(50),
	ACTIVITYCODE VARCHAR2(10),
    POINTSEARNED NUMBER(10),
	DATEOFACTIVITY DATE,
	CONSTRAINT pk_wallet primary key(SER),
	CONSTRAINT FK_WALLET1 FOREIGN KEY (BID) REFERENCES BRAND(BID),
	CONSTRAINT FK_WALLET2 FOREIGN KEY (ACTIVITYCODE) REFERENCES ACTIVITYTYPE(ACTIVITYCODE),
	CONSTRAINT fk_customerwallet FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID) ON DELETE CASCADE
);
/

CREATE TABLE WALLETRR(
    SER NUMBER(10) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	CUSTOMERID VARCHAR2(50),
	BID VARCHAR2(50),
	REWARDCODE VARCHAR2(10),
    POINTSREEDEMED NUMBER(10),
    USED NUMBER(1),
	DATEOFACTIVITY DATE,
	CONSTRAINT pk_walletRR primary key(SER),
	CONSTRAINT FK_WALLETRR1 FOREIGN KEY (BID) REFERENCES BRAND(BID),
	CONSTRAINT FK_WALLETRR2 FOREIGN KEY (REWARDCODE) REFERENCES REWARDTYPE(REWARDCODE),
	CONSTRAINT fk_customerwalletRR FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID) ON DELETE CASCADE
);
/


CREATE TABLE REVIEW(
	REVIEWID NUMBER(10) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	REVIEWTEXT VARCHAR2(200),
	CUSTOMERID VARCHAR2(50),
    BID VARCHAR2(50),
	CONSTRAINT PK_REVIEW PRIMARY KEY(REVIEWID),
	CONSTRAINT FK_REVIEW FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID) ON DELETE CASCADE,
    CONSTRAINT FK_REVIEW_BID FOREIGN KEY (BID) REFERENCES BRAND(BID) ON DELETE CASCADE
);
/


CREATE TABLE PURCHASE(
	PURCHASEID NUMBER(10) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	CUSTOMERID VARCHAR2(50),
	BID VARCHAR2(50),
	GCUSED NUMBER(1),
	CONSTRAINT PK_PURCHASE PRIMARY KEY (PURCHASEID),
	CONSTRAINT FK_PURCHASE FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID) ON DELETE CASCADE,
	CONSTRAINT FK_PURCHASE_BID FOREIGN KEY (BID) REFERENCES BRAND(BID) ON DELETE CASCADE,
	CONSTRAINT CON_PUR CHECK(GCUSED = 0 OR GCUSED = 1)
);
/


CREATE TABLE REDEEM(
	REDEEMID NUMBER(10) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
	REWARDCODE VARCHAR2(10),
	CUSTOMERID VARCHAR2(50),
    BID VARCHAR2(50),
	QUANTITY NUMBER(5),
	CONSTRAINT PK_REDEEM PRIMARY KEY(REDEEMID),
	CONSTRAINT FK_REDEEM_REWARD FOREIGN KEY (REWARDCODE) REFERENCES REWARDTYPE(REWARDCODE) ON DELETE CASCADE,
	CONSTRAINT FK_REDEEM FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID) ON DELETE CASCADE,
	CONSTRAINT FK_REDEEM_BID FOREIGN KEY (BID) REFERENCES BRAND(BID) ON DELETE CASCADE
);
/



CREATE TABLE REFERFRIEND(
    RFID NUMBER(10) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    CUSTOMERID VARCHAR2(50), --REFERRER ID
    BID VARCHAR2(50),
    CONSTRAINT PK_REFERFRIEND PRIMARY KEY(RFID),
	CONSTRAINT FK_REFERFRIEND_CUSTOMERID FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID),
    CONSTRAINT FK_REFERFRIEND_BID FOREIGN KEY (BID) REFERENCES BRAND(BID) ON DELETE CASCADE
);
/

---------------------------------User Define Types--------------------------------------

CREATE OR REPLACE TYPE wallet_type as OBJECT (
  BNAME VARCHAR2(80),
  ACNAME VARCHAR2(50),
  POINTS NUMBER(10),
  DOA DATE,
  ACTYPE VARCHAR2(1)
);
/

CREATE OR REPLACE TYPE wallet_table_type as TABLE OF wallet_type;
/


-----------------------------------Functions-----------------------------------------------

-- Function for getting next code ID
CREATE or REPLACE FUNCTION get_next_id
(id_key IN VARCHAR2)
RETURN VARCHAR2
IS
   NextID VARCHAR2(20);
   NextNumber NUMBER(10);
BEGIN

    SAVEPOINT start_tran;

    SELECT ID INTO NextNumber FROM TABKEY WHERE KEY = id_key;

    NextID := concat(id_key, CAST(NextNumber AS VARCHAR2));

    UPDATE TABKEY
    SET ID = ID + 1
    WHERE KEY = id_key;

    RETURN NextID;

    EXCEPTION
    WHEN OTHERS THEN
    ROLLBACK TO start_tran;
    RAISE;
END;
/

-- Retrieves point multilier for tier program
CREATE or REPLACE FUNCTION get_points_multiplier
(
custId IN VARCHAR2,
lCode IN VARCHAR2
)
RETURN NUMBER
IS
pointsEarned NUMBER(20);
pointsMultiplier NUMBER(5);
BEGIN
    SELECT POINTSEARNED INTO pointsEarned FROM ENROLLP WHERE CUSTOMERID = custId AND LPCODE = lCode;
    SELECT MULTIPLIER INTO pointsMultiplier
    FROM
    (
        SELECT MULTIPLIER FROM TIER WHERE LPCODE = lCode AND POINTSEARNED >= POINTS ORDER BY POINTS DESC
    ) TEMP
    WHERE ROWNUM = 1;

    RETURN pointsMultiplier;
END;
/

--- Show wallet information
CREATE OR REPLACE FUNCTION show_wallet_info
(
    custId VARCHAR2
)
  RETURN wallet_table_type PIPELINED AS
BEGIN
    FOR cur IN (
                    SELECT TEMP.BNAME, TEMP.ACNAME, TEMP.POINTS, TEMP.DATEOFACTIVITY DOA, TEMP.ACTYPE ACTYPE
                    FROM
                    (  
                        SELECT B.BNAME, AT.ACTIVITYNAME ACNAME, WRE.POINTSEARNED AS POINTS, DATEOFACTIVITY, 'E' AS ACTYPE 
                        FROM WALLETRE WRE INNER JOIN ACTIVITYTYPE AT ON WRE.ACTIVITYCODE= AT.ACTIVITYCODE
                        INNER JOIN BRAND B ON B.BID = WRE.BID
                        WHERE CUSTOMERID = custId
                        
                        UNION 
                        
                        SELECT B.BNAME, RT.REWARDNAME ACNAME, WRR.POINTSREEDEMED AS POINTS, DATEOFACTIVITY, 'R' AS ACTYPE  
                        FROM WALLETRR WRR INNER JOIN REWARDTYPE RT ON WRR.REWARDCODE= RT.REWARDCODE
                        INNER JOIN BRAND B ON B.BID = WRR.BID
                        WHERE CUSTOMERID = custId
                    ) TEMP
                    ORDER BY TEMP.DATEOFACTIVITY
    )
    LOOP
      PIPE ROW(wallet_type(cur.BNAME, cur.ACNAME, cur.POINTS, cur.DOA, cur.ACTYPE));
    END LOOP;
    RETURN;
END;
/


-----------------------------------Stored Procedures-----------------------------------------------

-- Adding a brand into marketplace by admin
CREATE or REPLACE PROCEDURE admin_add_brand
(
    brandId IN VARCHAR2,
    brandName IN VARCHAR2,
    brandAddress IN VARCHAR2,
    ret OUT INT
)
AS
OLDUSERIDCNT INT;
JOIN_DATE DATE;
BEGIN
    SELECT COUNT(USERID) INTO OLDUSERIDCNT FROM USERS WHERE USERID = brandId;

    IF OLDUSERIDCNT > 0 THEN
        ret := 0;
    ELSE
        SELECT CURRENT_DATE INTO JOIN_DATE FROM DUAL;
        -- Insert into users table
        INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES(brandId, '1234', 'B');
        -- Insert into brand table
        INSERT INTO BRAND(BID, BNAME, ADDRESS, JOINDATE) VALUES(brandId, brandName, brandAddress, JOIN_DATE);

        ret := 1;
    END IF;
END;
/

-- Adding a customer into marketplace by admin
CREATE or REPLACE PROCEDURE admin_add_customer
(
    customerId IN VARCHAR2,
    customerFName IN VARCHAR2,
    customerLName IN VARCHAR2,
    customerPhoneNumber IN VARCHAR2,
    customerAddress IN VARCHAR2,
    ret OUT INT
)
AS
OLDUSERIDCNT INT;
BEGIN
    SELECT COUNT(USERID) INTO OLDUSERIDCNT FROM USERS WHERE USERID = customerId;
    IF OLDUSERIDCNT > 0 THEN
        ret := 0;
    ELSE
        -- Insert into users table
        INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES(customerId, '1234', 'C');
        -- Insert into customer table
        INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS) VALUES(customerId, customerFName, customerLName, customerPhoneNumber, customerAddress);

        ret := 1;
    END IF;
END;
/

-- Adding a brand through brand signup
CREATE or REPLACE PROCEDURE add_brand
(
    brandId IN VARCHAR2,
    brandPassword IN VARCHAR2,
    brandName IN VARCHAR2,
    brandAddress IN VARCHAR2,
    ret OUT INT
)
AS
OLDUSERIDCNT INT;
JOIN_DATE DATE;
BEGIN
    SELECT COUNT(USERID) INTO OLDUSERIDCNT FROM USERS WHERE USERID = brandId;

    IF OLDUSERIDCNT > 0 THEN
        ret := 0;
    ELSE
        SELECT CURRENT_DATE INTO JOIN_DATE FROM DUAL;
        -- Insert into users table
        INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES(brandId, brandPassword, 'B');
        -- Insert into brand table
        INSERT INTO BRAND(BID, BNAME, ADDRESS, JOINDATE) VALUES(brandId, brandName, brandAddress, JOIN_DATE);

        ret := 1;
    END IF;
END;
/

-- Adding a customer through customer signup
CREATE or REPLACE PROCEDURE add_customer
(
    customerId IN VARCHAR2,
    customerPassword IN VARCHAR2,
    customerFName IN VARCHAR2,
    customerLName IN VARCHAR2,
    customerPhoneNumber IN VARCHAR2,
    customerAddress IN VARCHAR2,
    ret OUT INT
)
AS
OLDUSERIDCNT INT;
BEGIN
    SELECT COUNT(USERID) INTO OLDUSERIDCNT FROM USERS WHERE USERID = customerId;
    IF OLDUSERIDCNT > 0 THEN
        ret := 0;
    ELSE
        -- Insert into users table
        INSERT INTO USERS(USERID, PASSWORD, USERTYPE) VALUES(customerId, customerPassword, 'C');
        -- Insert into customer table
        INSERT INTO CUSTOMER(CUSTOMERID, FNAME, LNAME, PHONENUMBER, ADDRESS) VALUES(customerId, customerFName, customerLName, customerPhoneNumber, customerAddress);

        ret := 1;
    END IF;
END;
/

-- Brand enrollment in loyalty program
CREATE or REPLACE PROCEDURE enroll_brand_loyalty_program
(
    lpName IN VARCHAR2,
    lpType IN VARCHAR2,
    brandId IN VARCHAR2,
    lpCode OUT VARCHAR2
)
AS
BEGIN
        IF lpType = 'R' THEN
            lpCode := get_next_id('RLP');
        ELSE
            lpCode := get_next_id('TLP');
        END IF;
        -- Insert into loyalty program table
        INSERT INTO LOYALTYPROGRAM(LPCODE, LPNAME, LPTYPE, ISVALID, BRANDID) VALUES(lpCode, lpName, lpType, 0, brandId);
END;
/

-- Customer enrollment for loyalty program
CREATE or REPLACE PROCEDURE enroll_customer_loyalty_program
(
    custId IN VARCHAR2,
	lCode IN VARCHAR2,
	ret OUT INT
)
AS
OLDCUSTOMERIDCNT INT;
JOIN_DATE DATE;
BEGIN
	SELECT COUNT(CUSTOMERID) INTO OLDCUSTOMERIDCNT FROM ENROLLP WHERE CUSTOMERID = custId AND LPCODE = lCode;

    IF OLDCUSTOMERIDCNT > 0 THEN
        ret := 0;
	ELSE
       SELECT CURRENT_DATE INTO JOIN_DATE FROM DUAL;
        -- Insert into loyalty program table
        INSERT INTO ENROLLP(CUSTOMERID, LPCODE, POINTSEARNED, ENROLDATE) VALUES(custId, lCode, 0, JOIN_DATE);
        ret := 1;
	END IF;
END;
/

-- Adding reward earning rule
create or replace PROCEDURE add_re_rule
(
    bId IN VARCHAR2,
    acCode IN VARCHAR2,
    pts IN NUMBER,
    ret OUT INT
)
AS
SAMERULECOUNT INT;
ACTYPECOUNT INT;
BEGIN
    SELECT COUNT(BRANDID) INTO ACTYPECOUNT FROM BRANDACTIVITYTYPE WHERE BRANDID = bId AND ACTIVITYCODE = acCode;
    SELECT COUNT(BRANDID) INTO SAMERULECOUNT FROM RERULE WHERE BRANDID = bId AND ACTIVITYCODE = acCode;

    IF SAMERULECOUNT > 0 THEN
        ret := 0;
    ELSIF ACTYPECOUNT = 0 THEN
        ret := 2;
    ELSE
        -- Insert into rerule table
        INSERT INTO RERULE(BRANDID, ACTIVITYCODE, POINTS, VERSIONNO) values (bId, acCode, pts, 1);
        ret := 1;
    END IF;
END;
/

-- Updating reward earning rule
create or replace PROCEDURE update_re_rule
(
    bId IN VARCHAR2,
    acCode IN VARCHAR2,
    pts IN NUMBER,
    ret OUT INT
)
AS
CURRVNO INT;
BEGIN
    SELECT MAX(VERSIONNO) INTO CURRVNO FROM RERULE WHERE BRANDID = bId AND ACTIVITYCODE = acCode;

    IF CURRVNO > 0 THEN
        -- Insert into rerule table
        INSERT INTO RERULE(BRANDID, ACTIVITYCODE, POINTS, VERSIONNO) values (bId, acCode, pts, CURRVNO + 1);
        ret := 1;
    ELSE
        ret := 0;
    END IF;
END;
/

-- Adding reward redeeming rule
create or replace PROCEDURE add_rr_rule
(
    bId IN VARCHAR2,
    reCode IN VARCHAR2,
    pts IN NUMBER,
    ret OUT INT
)
AS
SAMERULECOUNT INT;
RETYPECOUNT INT;
BEGIN
    SELECT COUNT(BRANDID) INTO RETYPECOUNT FROM BRANDREWARDTYPE WHERE BRANDID = bId AND REWARDCODE = reCode;
    SELECT COUNT(BRANDID) INTO SAMERULECOUNT FROM RRRULE WHERE BRANDID = bId AND REWARDCODE = reCode;

    IF SAMERULECOUNT > 0 THEN
        ret := 0;
    ELSIF RETYPECOUNT = 0 THEN
        ret := 2;
    ELSE
        -- Insert into rerule table
        INSERT INTO RRRULE(BRANDID, REWARDCODE, POINTS, VERSIONNO) values (bId, reCode, pts, 1);
        ret := 1;
    END IF;
END;
/

-- Adding reward updating rule
create or replace PROCEDURE update_rr_rule
(
    bId IN VARCHAR2,
    reCode IN VARCHAR2,
    pts IN NUMBER,
    ret OUT INT
)
AS
CURRVNO INT;
BEGIN
    SELECT MAX(VERSIONNO) INTO CURRVNO FROM RRRULE WHERE BRANDID = bId AND REWARDCODE = reCode;

    IF CURRVNO > 0 THEN
        -- Insert into rrrule table
        INSERT INTO RRRULE(BRANDID, REWARDCODE, POINTS, VERSIONNO) values (bId, reCode, pts, CURRVNO + 1);
        ret := 1;
    ELSE
        ret := 0;
    END IF;
END;
/

-- validate loyalty program
create or replace PROCEDURE validate_loyalty_program
(
    bId IN VARCHAR2,
    lCode IN VARCHAR2,
    lpType IN VARCHAR2,
    ret OUT INT
)
AS
RERULECOUNT INT;
RRRULECOUNT INT;
TIERCOUNT INT;
BEGIN
    SELECT COUNT(DISTINCT ACTIVITYCODE) INTO RERULECOUNT FROM RERULE WHERE BRANDID = bId;
    SELECT COUNT(DISTINCT REWARDCODE) INTO RRRULECOUNT FROM RRRULE WHERE BRANDID = bId;

    IF lpType = 'T' THEN
        SELECT COUNT(DISTINCT TIERNAME) INTO TIERCOUNT FROM TIER WHERE LPCODE = lCode;
    END IF;

    IF lpType = 'T' AND TIERCOUNT < 1 THEN
        ret := 0;
    ELSIF RERULECOUNT < 1 THEN
        ret := 1;
    ELSIF RRRULECOUNT < 1 THEN
        ret := 2;
    ELSE
        UPDATE LOYALTYPROGRAM SET ISVALID = 1 WHERE BRANDID = bId;
        ret := 3;
    END IF;
END;
/

-- Customer purchase activity
create or replace PROCEDURE customer_add_purchase
(
    custId IN VARCHAR2,
    bId IN VARCHAR2,
    lCode IN VARCHAR2,
    acCode IN VARCHAR2,
    isGCUsed IN NUMBER
)
AS
RulePoints NUMBER(10);
LType VARCHAR2(1);
pointsMultiplier NUMBER(5);
totalPoints NUMBER(10);
TodayDate DATE;
BEGIN
   -- Get today's date
   SELECT CURRENT_DATE INTO TodayDate FROM DUAL;
    -- Insert into purchase table
    INSERT INTO PURCHASE(CUSTOMERID, BID, GCUSED) VALUES(custId, bId, isGCUsed);

    IF isGCUsed = 1 THEN
        -- Make the gift card used
        UPDATE WALLETRR SET USED = 1
        WHERE ser = (SELECT ser
        FROM WALLETRR
        WHERE CUSTOMERID = custId AND BID = bId AND USED = 0 AND ROWNUM = 1);

        totalPoints := 0;
    ELSE
        -- Get points from RE rule
        SELECT POINTS INTO RulePoints FROM RERULE where ACTIVITYCODE = acCode AND BRANDID = bId
           AND VERSIONNO = (SELECT MAX(VERSIONNO) FROM RERULE where ACTIVITYCODE = acCode AND BRANDID = bId);
        -- Get Tier Type
        SELECT LPTYPE INTO LType FROM LOYALTYPROGRAM WHERE LPCODE = lCode;

        IF LType = 'T' THEN
           pointsMultiplier := get_points_multiplier(custId, lCode);
        ELSE
           pointsMultiplier := 1;
        END IF;

        totalPoints := pointsMultiplier * RulePoints;
     END IF;

    -- Insert into WALLETRE table
    INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY)
    VALUES(custId, bId, acCode, totalPoints, TodayDate);

    -- Update ENROLLP table
    UPDATE ENROLLP
    SET POINTSEARNED = POINTSEARNED + totalPoints
    WHERE CUSTOMERID = custId AND LPCODE = lCode;
END;
/

-- Customer adding review
create or replace PROCEDURE customer_add_review
(
    custId IN VARCHAR2,
    bId IN VARCHAR2,
    lCode IN VARCHAR2,
    acCode IN VARCHAR2,
    review_text IN VARCHAR2
)
AS
RulePoints NUMBER(10);
LType VARCHAR2(1);
pointsMultiplier NUMBER(5);
totalPoints NUMBER(10);
TodayDate DATE;
BEGIN
   -- Get today's date
   SELECT CURRENT_DATE INTO TodayDate FROM DUAL;
    -- Insert into review table
    INSERT INTO REVIEW(REVIEWTEXT, CUSTOMERID, BID) VALUES(review_text, custId, bId);

    -- Get points from RE rule
    SELECT POINTS INTO RulePoints FROM RERULE where ACTIVITYCODE = acCode AND BRANDID = bId
       AND VERSIONNO = (SELECT MAX(VERSIONNO) FROM RERULE where ACTIVITYCODE = acCode AND BRANDID = bId);
    -- Get Tier Type
    SELECT LPTYPE INTO LType FROM LOYALTYPROGRAM WHERE LPCODE = lCode;

    IF LType = 'T' THEN
       pointsMultiplier := get_points_multiplier(custId, lCode);
    ELSE
       pointsMultiplier := 1;
    END IF;

    totalPoints := pointsMultiplier * RulePoints;

    -- Insert into WALLETRE table
    INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY)
    VALUES(custId, bId, acCode, totalPoints, TodayDate);

    -- Update ENROLLP table
    UPDATE ENROLLP
    SET POINTSEARNED = POINTSEARNED + totalPoints
    WHERE CUSTOMERID = custId AND LPCODE = lCode;
END;
/

-- Customer referring a friend
create or replace PROCEDURE customer_refer_friend
(
    custId IN VARCHAR2,
    bId IN VARCHAR2,
    lCode IN VARCHAR2,
    acCode IN VARCHAR2
)
AS
RulePoints NUMBER(10);
LType VARCHAR2(1);
pointsMultiplier NUMBER(5);
totalPoints NUMBER(10);
TodayDate DATE;
BEGIN
   -- Get today's date
   SELECT CURRENT_DATE INTO TodayDate FROM DUAL;
    -- Insert into review table
    INSERT INTO REFERFRIEND(CUSTOMERID, BID) VALUES(custId, bId);

    -- Get points from RE rule
    SELECT POINTS INTO RulePoints FROM RERULE where ACTIVITYCODE = acCode AND BRANDID = bId
       AND VERSIONNO = (SELECT MAX(VERSIONNO) FROM RERULE where ACTIVITYCODE = acCode AND BRANDID = bId);
    -- Get Tier Type
    SELECT LPTYPE INTO LType FROM LOYALTYPROGRAM WHERE LPCODE = lCode;

    IF LType = 'T' THEN
       pointsMultiplier := get_points_multiplier(custId, lCode);
    ELSE
       pointsMultiplier := 1;
    END IF;

    totalPoints := pointsMultiplier * RulePoints;

    -- Insert into WALLETRE table
    INSERT INTO WALLETRE(CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, DATEOFACTIVITY)
    VALUES(custId, bId, acCode, totalPoints, TodayDate);

    -- Update ENROLLP table
    UPDATE ENROLLP
    SET POINTSEARNED = POINTSEARNED + totalPoints
    WHERE CUSTOMERID = custId AND LPCODE = lCode;
END;
/

-- Retrieve customer reward points
create or replace PROCEDURE customer_reward_points
(
    custId IN VARCHAR2,
    brandId IN VARCHAR2,
    ret OUT NUMBER
) 
AS
earningPoints NUMBER;
redeemedPoint NUMBER;
BEGIN
    SELECT NVL(SUM(NVL(POINTSEARNED, 0)), 0) INTO earningPoints FROM WALLETRE WHERE CUSTOMERID = custId AND BID = brandId; 
    SELECT NVL(SUM(NVL(POINTSREEDEMED, 0)), 0) INTO redeemedPoint FROM WALLETRR WHERE CUSTOMERID = custId AND BID = brandId;
    
    ret := earningPoints - redeemedPoint;
END;
/

-- customer redeem
create or replace PROCEDURE customer_redeem
(
    custId IN VARCHAR2,
    bId IN VARCHAR2,
    lCode IN VARCHAR,
    rrCode IN VARCHAR,
    quantity IN NUMBER,
    custCurrPoints IN NUMBER,
    ret OUT INT
) 
AS
totalPointsReq NUMBER;
pointsRemained NUMBER;
RulePoints NUMBER;
DATE_OF_ACT DATE;
BEGIN
    SELECT CURRENT_DATE INTO DATE_OF_ACT FROM DUAL;

    SELECT POINTS INTO RulePoints FROM RRRULE where REWARDCODE = rrCode AND BRANDID = bId
       AND VERSIONNO = (SELECT MAX(VERSIONNO) FROM RRRULE where REWARDCODE = rrCode AND BRANDID = bId);
    
    totalPointsReq :=  RulePoints * quantity; 
    
    IF custCurrPoints >= totalPointsReq THEN
        INSERT INTO REDEEM(REWARDCODE, CUSTOMERID, BID, QUANTITY) VALUES(rrCode, custId, bId, quantity);
        
        FOR l_counter IN 1..quantity
        LOOP
            INSERT INTO WALLETRR(CUSTOMERID, BID, REWARDCODE, POINTSREEDEMED, USED, DATEOFACTIVITY) 
            VALUES(custId, bId, rrCode, RulePoints, 0, DATE_OF_ACT);
        END LOOP;
        
        ret := 1;
    ELSE
        ret := 0;
    END IF;
END;
/
