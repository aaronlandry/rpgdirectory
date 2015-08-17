/* UPDATE AS APPROPRIATE FOR SYSTEM */

CREATE BIGFILE TABLESPACE RPG_DATA
  DATAFILE 'C:\APP\DBORACLE\ORADATA\RPG_DATA.DBF'
    SIZE 10M
    AUTOEXTEND ON;

CREATE TABLESPACE RPG_IDX
  DATAFILE 'C:\APP\DBORACLE\ORADATA\RPG_IDX.dat' 
    SIZE 10M
    REUSE
    AUTOEXTEND ON NEXT 10M MAXSIZE 500M;

