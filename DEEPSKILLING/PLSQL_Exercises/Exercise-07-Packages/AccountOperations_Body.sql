CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount (
        p_account_id IN NUMBER,
        p_customer_id IN NUMBER,
        p_account_type IN VARCHAR2,
        p_initial_balance IN NUMBER
    ) IS
        v_customer_count NUMBER;
        v_account_count NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_customer_count
        FROM Customers
        WHERE CustomerID = p_customer_id;

        IF v_customer_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20014, 'Customer does not exist');
        END IF;

        SELECT COUNT(*) INTO v_account_count
        FROM Accounts
        WHERE AccountID = p_account_id;

        IF v_account_count > 0 THEN
            RAISE_APPLICATION_ERROR(-20015, 'Account already exists');
        END IF;

        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_initial_balance, SYSDATE);

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error opening account: ' || SQLERRM);
    END OpenAccount;

    PROCEDURE CloseAccount (
        p_account_id IN NUMBER
    ) IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = p_account_id;

        IF v_balance != 0 THEN
            RAISE_APPLICATION_ERROR(-20016, 'Account balance must be zero before closing');
        END IF;

        DELETE FROM Accounts WHERE AccountID = p_account_id;

        COMMIT;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error: Account not found');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error closing account: ' || SQLERRM);
    END CloseAccount;

    FUNCTION GetTotalBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_total_balance NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;

        RETURN v_total_balance;
    END GetTotalBalance;

END AccountOperations;
/

SET SERVEROUTPUT ON;

DECLARE
    v_total NUMBER;
BEGIN
    AccountOperations.OpenAccount(6, 1, 'Savings', 0);
    v_total := AccountOperations.GetTotalBalance(1);
    DBMS_OUTPUT.PUT_LINE('CustomerID 1 total balance across all accounts: ' || v_total);
    AccountOperations.CloseAccount(6);
END;
/
