--Get all fields and records from customer

SELECT * FROM customer;

--Get all fields from customer, but only if they are from Arizona

SELECT * FROM customer WHERE state = 'AZ';

--Get all invoices older than 6 months 

SELECT * FROM invoice WHERE invoice_date < NOW() - INTERVAL '6 months';

--Update all customer phone numbers to NULL if they don’t follow this format: ‘+1 555 555-5555’

UPDATE customer SET phone = NULL WHERE phone !~ '^\+1 [0-9]{3} [0-9]{3}-[0-9]{4}$';

--Get all tracks that are longer than 180000 milliseconds

SELECT * from track WHERE milliseconds > 180000;

--Update all customers not in the USA so that their country=USA and address, city, & state are NULL

UPDATE customer SET country = 'USA', address = NULL, city = NULL, state = NULL WHERE NOT country = 'USA';

--Given a customer_id, return their total spending across all invoices using a function

CREATE OR REPLACE FUNCTION get_customer_total(p_customer_id INT)
RETURNS NUMERIC
AS $$
    SELECT SUM(total)
    FROM invoice
    WHERE customer_id = p_customer_id;
$$
LANGUAGE plpgSQL;

SELECT get_customer_total(1);

-- Given an employee_id + new_manager_id, create a stored procedure to update an Employee’s ReportsTo field.
-- Prevent an employee reporting to themselves, reporting to a non-existence employee, or creating a circular management relationship

CREATE PROCEDURE updateEmployeeReportsTo(employee_id_p NUMERIC, new_manager_id NUMERIC)
LANGUAGE plpgSQL
AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM employee WHERE employee_id = new_manager_id) 
        AND employee_id_p != new_manager_id 
        AND NOT EXISTS (SELECT 1 FROM employee WHERE reports_to = employee_id_p and employee_id = new_manager_id)
        THEN UPDATE employee SET reports_to = new_manager_id WHERE employee_id = employee_id_p;
    END IF;
END;
$$;

-- Create a new schema: pets
-- Create two related tables: Customer + Pets
-- Demonstrate populating records into these tables

CREATE SCHEMA pets;
CREATE TABLE pets.Customer(
    customer_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR(32),
    phone VARCHAR(16)
);
CREATE TABLE pets.Pets(
    pet_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pet_type VARCHAR(32),
    pet_color VARCHAR(32),
    owner_id INTEGER,
    CONSTRAINT pet_owner_id_fkey
    FOREIGN KEY (owner_id) 
    REFERENCES pets.Customer (customer_id) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION
);

INSERT INTO pets.Customer (email, phone) VALUES ('stevejones@yahoo.com', '817-321-7654');
INSERT INTO pets.Customer (email, phone) VALUES ('marysmith@gmail.com', '682-963-8521');

INSERT INTO pets.Pets (pet_type, pet_color, owner_id) VALUES ('Dog', 'Brown', 1);
INSERT INTO pets.Pets (pet_type, pet_color, owner_id) VALUES ('Cat', 'White', 1);
INSERT INTO pets.Pets (pet_type, pet_color, owner_id) VALUES ('Cat', 'Black', 2);