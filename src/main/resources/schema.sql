CREATE TABLE IF NOT EXISTS Ingredient (
    id varchar(4) NOT NULL PRIMARY KEY,
    name varchar(25) NOT NULL,
    TYPE varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco (
    id identity,
    taconame varchar(50) NOT NULL,
    createdAt timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco_Ingredients (
    taco bigint NOT NULL,
    ingredient varchar(4) NOT NULL
);

ALTER TABLE
    Taco_Ingredients
ADD
    FOREIGN KEY (taco) REFERENCES Taco(id);

ALTER TABLE
    Taco_Ingredients
ADD
    FOREIGN KEY (ingredient) REFERENCES Ingredient(id);

CREATE TABLE IF NOT EXISTS Taco_Order (
    id identity,
    name varchar(50) NOT NULL,
    street varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(50) NOT NULL,
    zip varchar(10) NOT NULL,
    ccNumber varchar(16) NOT NULL,
    ccExpiration varchar(5) NOT NULL,
    ccCVV varchar(3) NOT NULL,
    placedAt timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco_Order_Tacos (
    tacoOrder bigint NOT NULL,
    taco bigint NOT NULL
);

ALTER TABLE
    Taco_Order_Tacos
ADD
    FOREIGN KEY (tacoOrder) REFERENCES Taco_Order(id);

ALTER TABLE
    Taco_Order_Tacos
ADD
    FOREIGN KEY (taco) REFERENCES Taco(id);