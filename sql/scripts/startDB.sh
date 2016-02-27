#! /bin/bash
pg_ctl -o "-p $PGPORT" -D $PGDATA -l logfile start
# source ~/.bashrc
echo PGPORT: $PGPORT
echo PGDATA: $PGDATA
echo DB_NAME: $DB_NAME
echo USER: $USER
# createdb -p $PGPORT $DB_NAME
pg_ctl status
ps aux | grep "$USER" | grep postmaster
