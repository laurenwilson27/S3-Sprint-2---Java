CREATE TABLE health_data (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    weight DECIMAL(5,2) NOT NULL,
    height DECIMAL(5,2) NOT NULL,
    steps INT NOT NULL,
    heart_rate INT NOT NULL,
    date DATE NOT NULL,
    -- I've added a Hemoglobin A1C column. This is expressed as a percentage, and we'll use one decimal point.
    a1c DECIMAL(2,1) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
)