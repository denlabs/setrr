-- This is already done
-- ALTER USER postgres WITH PASSWORD 'born1975Feb28';

-- TODO how to move the secrets to keyvault?
CREATE USER setrr WITH PASSWORD 'stickynote5432';
CREATE SCHEMA setrr AUTHORIZATION setrr;
GRANT ALL PRIVILEGES ON SCHEMA setrr TO setrr;