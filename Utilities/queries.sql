--Query1:

SELECT C.CUSTOMERID CUSTID, C.FNAME CUSTFNAME, C.LNAME CUSTLNAME
FROM CUSTOMER C
INNER JOIN ENROLLP E
ON E.CUSTOMERID = C.CUSTOMERID
INNER JOIN LOYALTYPROGRAM L
ON L.LPCODE = E.LPCODE
WHERE L.BRANDID = 'Brand02';

--Query2:

SELECT E.CUSTOMERID CUSTID, E.LPCODE LCODE
FROM ENROLLP E
INNER JOIN LOYALTYPROGRAM L
ON E.LPCODE = L.LPCODE
WHERE EXISTS (SELECT SER FROM WALLETRE WE WHERE WE.CUSTOMERID = E.CUSTOMERID AND WE.BID = L.BRANDID)
OR EXISTS (SELECT SER FROM WALLETRR WR WHERE WR.CUSTOMERID = E.CUSTOMERID AND WR.BID = L.BRANDID)
ORDER BY E.CUSTOMERID;

--Query3:

SELECT R.REWARDCODE RCODE, R.REWARDNAME RNAME
FROM BRANDREWARDTYPE BR 
INNER JOIN REWARDTYPE R
ON R.REWARDCODE = BR.REWARDCODE
WHERE BR.BRANDID = 'Brand01';

--Query4:

SELECT L.LPCODE LCODE, L.LPNAME LNAME
FROM LOYALTYPROGRAM L
WHERE EXISTS (SELECT RE.BRANDID FROM RERULE RE WHERE RE.BRANDID = L.BRANDID AND RE.ACTIVITYCODE = 'A03');