SET SERVEROUTPUT ON;

DECLARE
    CURSOR CustomerCursor IS
        SELECT CustomerID, DOB FROM Customers;
    v_age NUMBER;
BEGIN
    FOR CustomerRec IN CustomerCursor LOOP
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, CustomerRec.DOB) / 12);
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - (InterestRate * 0.01)
            WHERE CustomerID = CustomerRec.CustomerID;
            DBMS_OUTPUT.PUT_LINE('CustomerID ' || CustomerRec.CustomerID || ' Age ' || v_age || ' - Loan interest discount applied');
        END IF;
    END LOOP;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error applying loan interest discount: ' || SQLERRM);
END;
/
