CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) AS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM Customers
    WHERE CustomerID = p_customer_id;

    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Customer with this ID already exists');
    END IF;

    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE, 'N');

    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Duplicate customer ID');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
END AddNewCustomer;
/

SET SERVEROUTPUT ON;

BEGIN
    AddNewCustomer(6, 'New Customer', TO_DATE('1999-01-01', 'YYYY-MM-DD'), 500);
END;
/

BEGIN
    AddNewCustomer(1, 'Duplicate Customer', TO_DATE('1999-01-01', 'YYYY-MM-DD'), 500);
END;
/
