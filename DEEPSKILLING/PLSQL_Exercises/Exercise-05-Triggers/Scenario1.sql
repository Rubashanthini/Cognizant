CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/

SET SERVEROUTPUT ON;

UPDATE Customers SET Balance = Balance + 50 WHERE CustomerID = 1;

SELECT CustomerID, LastModified FROM Customers WHERE CustomerID = 1;
