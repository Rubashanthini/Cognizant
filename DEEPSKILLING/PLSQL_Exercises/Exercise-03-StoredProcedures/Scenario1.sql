CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    CURSOR SavingsCursor IS
        SELECT AccountID, Balance
        FROM Accounts
        WHERE AccountType = 'Savings'
        FOR UPDATE;
BEGIN
    FOR AccountRec IN SavingsCursor LOOP
        UPDATE Accounts
        SET Balance = Balance + (Balance * 0.01), LastModified = SYSDATE
        WHERE CURRENT OF SavingsCursor;
    END LOOP;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/

SET SERVEROUTPUT ON;

BEGIN
    ProcessMonthlyInterest;
END;
/
