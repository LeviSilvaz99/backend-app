FROM postgres:13.1-alpine
LABEL maintainer "levisilvaz99 <andrei_e.n.d@hotmail.com>"
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=12345678
ENV POSTGRES_DB=postgres-db
EXPOSE 5432
