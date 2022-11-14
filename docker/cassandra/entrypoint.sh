#!/bin/bash

for f in /cassandra-init-scripts/*; do
    case "$f" in
        *.sh)     echo "$0: running $f"; . "$f" ;;
        *.cql)    echo "$0: running $f" && until cqlsh -u cassandra -p cassandra -f "$f"; do >&2 echo "Cassandra is unavailable - sleeping"; sleep 2; done & ;;
        *)        echo "$0: ignoring $f" ;;
    esac
    echo
done

exec /usr/local/bin/docker-entrypoint.sh "$@"
