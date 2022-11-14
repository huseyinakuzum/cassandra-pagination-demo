# Cassandra for Docker

With docker-compose file on this folder we are going to set up a Cassandra cluster in our local machine using docker.

The only prerequisite is docker.

### The volume folders

Navigate to the home directory on your machine and create a directory called cassandra `$HOME/docker/volumes/cassandra`.

We will be mounting this directory to the data directory of the cassandra container. In our docker-compose.yml file there are these lines:

    volumes:
        - $HOME/docker/volumes/cassandra:/var/lib/cassandra


This ensures that the data on our container persists even when the container is stopped and restarted later. So, we won't lose your indices when we restart the containers.

If you get an error like one below;
        
    cassandra-compose | org.apache.cassandra.exceptions.ConfigurationException: Saved cluster name demo != configured name demo-cluster

Then simply remove the cassandra volume folder in your home directory. 
```bash
docker-compose up --build
# ...
cassandra-compose  | INFO  [main] 2022-03-14 08:44:26,637 CassandraDaemon.java:780 - Startup complete
```

