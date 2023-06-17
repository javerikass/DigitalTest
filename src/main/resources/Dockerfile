FROM postgres:14.1

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD pass
ENV POSTGRES_DB test

EXPOSE 5432

CMD ["postgres"]

COPY init.sql /docker-entrypoint-initdb.d/
