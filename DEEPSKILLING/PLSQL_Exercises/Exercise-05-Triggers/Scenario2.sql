CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (LogID, TransactionID, AccountID, Amount, TransactionType, LogDate)
    VALUES (AuditLog_Seq.NEXTVAL, :NEW.TransactionID, :NEW.AccountID, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END LogTransaction;
/

SET SERVEROUTPUT ON;

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (5, 5, SYSDATE, 150, 'Deposit');

COMMIT;

SELECT LogID, TransactionID, AccountID, Amount, TransactionType FROM AuditLog;
