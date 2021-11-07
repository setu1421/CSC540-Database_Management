--Query1:

SELECT OC.CUSTOMERID CUSTID, OC.FNAME CUSTFNAME, OC.LNAME CUSTLNAME
FROM CUSTOMER OC
WHERE OC.CUSTOMERID NOT IN (
SELECT C.CUSTOMERID
FROM CUSTOMER C
INNER JOIN ENROLLP E
ON E.CUSTOMERID = C.CUSTOMERID
INNER JOIN LOYALTYPROGRAM L
ON L.LPCODE = E.LPCODE
WHERE L.BRANDID = 'Brand02');

--Query2:

SELECT E.CUSTOMERID CUSTID, E.LPCODE LCODE
FROM ENROLLP E
INNER JOIN LOYALTYPROGRAM L
ON E.LPCODE = L.LPCODE
WHERE NOT EXISTS (SELECT SER FROM WALLETRE WE WHERE WE.CUSTOMERID = E.CUSTOMERID AND WE.BID = L.BRANDID)
AND NOT EXISTS (SELECT SER FROM WALLETRR WR WHERE WR.CUSTOMERID = E.CUSTOMERID AND WR.BID = L.BRANDID)
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

-- Query5

SELECT BA.ACTIVITYCODE ACODE, A.ACTIVITYNAME ANAME, COUNT(SER) CNT 
FROM BRANDACTIVITYTYPE BA
INNER JOIN ACTIVITYTYPE A
ON A.ACTIVITYCODE = BA.ACTIVITYCODE
INNER JOIN WALLETRE WE
ON WE.BID = BA.BRANDID AND BA.ACTIVITYCODE = WE.ACTIVITYCODE
WHERE BA.BRANDID = 'Brand01'
GROUP BY BA.ACTIVITYCODE, A.ACTIVITYNAME

-- Query6

SELECT E.CUSTOMERID AS CUSID, C.FNAME FNAME, C.LNAME LNAME
FROM ENROLLP E
INNER JOIN LOYALTYPROGRAM L
ON E.LPCODE = L.LPCODE
INNER JOIN CUSTOMER C
ON C.CUSTOMERID = E.CUSTOMERID
WHERE L.BRANDID = 'Brand01' 
AND 2 <= (SELECT COUNT(WR.SER) FROM WALLETRR WR WHERE WR.BID = 'Brand01' AND WR.CUSTOMERID = E.CUSTOMERID);

-- Query7

SELECT WR.BID BRANDID, B.BNAME BRANDNAME, SUM(POINTSREEDEMED) TOTPOINTS
FROM WALLETRR WR
INNER JOIN BRAND B
ON B.BID = WR.BID
GROUP BY WR.BID, B.BNAME
HAVING SUM(POINTSREEDEMED) < 500;

-- Query8

SELECT COUNT(SER) AS CNT
FROM
(
SELECT SER
FROM WALLETRE
WHERE CUSTOMERID = 'C0003' AND BID = 'Brand02' 
AND DATEOFACTIVITY BETWEEN TO_DATE('01/08/2021', 'DD/MM/YYYY') AND TO_DATE('30/09/2021', 'DD/MM/YYYY')

UNION

SELECT SER 
FROM WALLETRR
WHERE CUSTOMERID = 'C0003' AND BID = 'Brand02' 
AND DATEOFACTIVITY BETWEEN TO_DATE('01/08/2021', 'DD/MM/YYYY') AND TO_DATE('30/09/2021', 'DD/MM/YYYY')
)