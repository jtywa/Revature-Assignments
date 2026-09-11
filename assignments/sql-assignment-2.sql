-- 1. Get all invoice ids with the customers first name, last name, and the invoice total

SELECT invoice.invoice_id, customer.first_name, customer.last_name, invoice.total 
    FROM invoice 
    INNER JOIN customer 
    ON invoice.customer_id = customer.customer_id;

-- 2. Print the invoice id, customer's first name, and invoice total. But only if the invoice is over $30.

SELECT i.invoice_id, c.first_name, i.total 
    FROM invoice i 
    INNER JOIN customer c 
    ON i.customer_id = c.customer_id 
    WHERE i.total > 30;

-- 3. Get all the invoices for USA customers in the last 6 months. Use a CTE. 

WITH invoices_usa_six_months AS (
    SELECT * FROM invoice 
    WHERE billing_country = 'USA' 
    AND invoice_date >= NOW() - INTERVAL '6 months'
)
SELECT * FROM invoices_usa_six_months;

-- CHALLENGES PT. 2

-- Create a new table called record_logs
-- Fields: log_id, record_id, field_changed, last_update, old_value, new_value

CREATE TABLE record_logs(
    log_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
    record_id INT REFERENCES(customer.customer_id), 
    field_changed TEXT, 
    last_update TIMESTAMPTZ, 
    old_value TEXT,
    new_value TEXT
);

-- Create a trigger that tracks changes to customer records and logs the changes in our new table

CREATE OR REPLACE FUNCTION log_change()
RETURNS TRIGGER 
LANGUAGE plpgsql
AS $$
BEGIN
    IF OLD.first_name IS DISTINCT FROM NEW.first_name THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'first_name', NOW(),OLD.first_name, NEW.first_name);
    END IF;

    IF OLD.last_name IS DISTINCT FROM NEW.last_name THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'last_name', NOW(),OLD.last_name, NEW.last_name);
    END IF;

    IF OLD.company IS DISTINCT FROM NEW.company THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'company', NOW(),OLD.company, NEW.company);
    END IF;

    IF OLD.address IS DISTINCT FROM NEW.address THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'address', NOW(),OLD.address, NEW.address);
    END IF;

    IF OLD.city IS DISTINCT FROM NEW.city THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'city', NOW(),OLD.city, NEW.city);
    END IF;

    IF OLD.state IS DISTINCT FROM NEW.state THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'state', NOW(),OLD.state, NEW.state);
    END IF;

    IF OLD.country IS DISTINCT FROM NEW.country THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'country', NOW(),OLD.country, NEW.country);
    END IF;

    IF OLD.postal_code IS DISTINCT FROM NEW.postal_code THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'postal_code', NOW(),OLD.postal_code, NEW.postal_code);
    END IF;

    IF OLD.phone IS DISTINCT FROM NEW.phone THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'phone', NOW(),OLD.phone, NEW.phone);
    END IF;

    IF OLD.fax IS DISTINCT FROM NEW.fax THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'fax', NOW(),OLD.fax, NEW.fax);
    END IF;

    IF OLD.email IS DISTINCT FROM NEW.email THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'email', NOW(),OLD.email, NEW.email);
    END IF;

    IF OLD.support_rep_id IS DISTINCT FROM NEW.support_rep_id THEN
        INSERT INTO record_logs(record_id, field_changed, last_update, old_value, new_value)
        VALUES (OLD.customer_id, 'support_rep_id', NOW(),OLD.support_rep_id, NEW.support_rep_id);
    END IF;
    RETURN NEW;
END;
$$;

SELECT * FROM customer;

CREATE TRIGGER customer_log_change
BEFORE UPDATE ON customer
FOR EACH ROW
EXECUTE FUNCTION log_change();