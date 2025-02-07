CREATE SEQUENCE routes_id_seq START 1; -- Create a sequence for the id column
GRANT USAGE ON SEQUENCE routes_id_seq TO setrr;

CREATE TABLE routes (
    id BIGINT DEFAULT nextval('routes_id_seq') PRIMARY KEY, -- id field with auto-increment
    setter_name VARCHAR(255), -- setterName field
    description VARCHAR(1000),
    proposed_grade VARCHAR(255), -- proposedGrade field
    anchor_name VARCHAR(255), -- anchorName field
    set_type VARCHAR(255), -- setType field
    color VARCHAR(255) -- color field
);