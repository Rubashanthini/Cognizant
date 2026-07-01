SET SERVEROUTPUT ON;

DECLARE
    CURSOR CustomerCursor IS
        SELECT CustomerID, Balance FROM Customers;
BEGIN
    FOR CustomerRec IN CustomerCursor LOOP
        IF CustomerRec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'Y'
            WHERE CustomerID = CustomerRec.CustomerID;
            DBMS_OUTPUT.PUT_LINE('CustomerID ' || CustomerRec.CustomerID || ' promoted to VIP status');
        ELSE
            UPDATE Customers
            SET IsVIP = 'N'
            WHERE CustomerID = CustomerRec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating VIP status: ' || SQLERRM);
END;
/
