FROM mysql:8.0-oracle
ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_DATABASE demo
ADD ./db.sql /docker-entrypoint-initdb.d
EXPOSE 3306
