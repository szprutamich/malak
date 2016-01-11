set czas=%TIME::=-%
set czas=%czas:~0,5%
set nazwa=%DATE%@%CZAS%
mysqldump -umalak -pmalak --default-character-set=utf8 --single-transaction=TRUE malak > malak_%nazwa%.sql
