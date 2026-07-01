SET SERVEROUTPUT ON;

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate
        FROM Loans
        FOR UPDATE;
    v_new_rate NUMBER;
BEGIN
    FOR LoanRec IN UpdateLoanInterestRates LOOP
        IF LoanRec.InterestRate < 6 THEN
            v_new_rate := LoanRec.InterestRate + 0.5;
        ELSE
            v_new_rate := LoanRec.InterestRate - 0.25;
        END IF;

        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE CURRENT OF UpdateLoanInterestRates;

        DBMS_OUTPUT.PUT_LINE('LoanID: ' || LoanRec.LoanID || ' updated to new interest rate: ' || v_new_rate);
    END LOOP;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating loan interest rates: ' || SQLERRM);
END;
/
