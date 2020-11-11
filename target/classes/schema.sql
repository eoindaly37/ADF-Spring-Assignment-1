CREATE TABLE household (
    eircode varchar(30) NOT NULL PRIMARY KEY ,
    address varchar(30) NOT NULL
);
CREATE TABLE occupant (
    id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(30) NOT NULL,
    age int(11) NOT NULL,
    occupation varchar(30) NOT NULL,
    eircode varchar(30) NOT NULL REFERENCES household(eircode)
);