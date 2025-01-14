INSERT INTO usuario (nombre, apellido, email, password, fecha, role)
SELECT 'Christian', 'Ramirez', 'admin@gmail.com', '$2a$10$WlAZsv71Kd8b/Dwfw9fEG.j27wDMfmLYR2tn8Fhu1MFzmQXCryO3y', NOW(), 'ADMIN'
    WHERE NOT EXISTS (
    SELECT 1 FROM usuario WHERE email = 'admin@gmail.com'
);
