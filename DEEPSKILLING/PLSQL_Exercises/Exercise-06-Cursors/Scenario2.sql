SET SERVEROUTPUT ON;

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE;
    v_fee CONSTANT NUMBER := 25;
BEGIN
    FOR FeeRec IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - v_fee, LastModified = SYSDATE
        WHERE CURRENT OF ApplyAnnualFee;
        DBMS_OUTPUT.PUT_LINE('Annual fee of ' || v_fee || ' deducted from AccountID: ' || FeeRec.AccountID);
    END LOOP;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error applying annual fee: ' || SQLERRM);
END;
/
