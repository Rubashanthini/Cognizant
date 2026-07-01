SET SERVEROUTPUT ON;

DECLARE
    CURSOR LoanCursor IS
        SELECT l.LoanID, l.CustomerID, c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR LoanRec IN LoanCursor LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || LoanRec.LoanID || ' for customer ' || LoanRec.Name || ' is due on ' || TO_CHAR(LoanRec.EndDate, 'DD-MON-YYYY'));
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error generating loan reminders: ' || SQLERRM);
END;
/
