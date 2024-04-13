CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_doctor BOOLEAN NOT NULL,
    -- Additional columns used by Doctors - these can be NULL, as not all Users are Doctors
    medical_license_number VARCHAR(50),
    specialization VARCHAR(50)
)