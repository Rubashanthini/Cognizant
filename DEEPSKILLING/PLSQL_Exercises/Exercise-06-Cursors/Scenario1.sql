SET SERVEROUTPUT ON;

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE TO_CHAR(t.TransactionDate, 'MM-YYYY') = TO_CHAR(SYSDATE, 'MM-YYYY')
        ORDER BY c.CustomerID;
    v_current_customer NUMBER := -1;
BEGIN
    FOR StatementRec IN GenerateMonthlyStatements LOOP
        IF v_current_customer != StatementRec.CustomerID THEN
            DBMS_OUTPUT.PUT_LINE('Statement for Customer: ' || StatementRec.Name);
            v_current_customer := StatementRec.CustomerID;
        END IF;
        DBMS_OUTPUT.PUT_LINE('  Transaction ' || StatementRec.TransactionID
                              || ' | Date: ' || TO_CHAR(StatementRec.TransactionDate, 'DD-MON-YYYY')
                              || ' | Type: ' || StatementRec.TransactionType
                              || ' | Amount: ' || StatementRec.Amount);
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error generating monthly statements: ' || SQLERRM);
END;
/
