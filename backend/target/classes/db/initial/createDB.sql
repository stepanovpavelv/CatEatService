CREATE ROLE cat_admin WITH LOGIN PASSWORD '123';
ALTER ROLE cat_admin WITH SUPERUSER;
ALTER ROLE cat_admin WITH CREATEDB;

CREATE DATABASE "CatEat" WITH OWNER cat_admin;