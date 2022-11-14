-- Important: remember to copy updated script to test resources to run test containers
CREATE KEYSPACE demo WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

create table if not exists demo.connection_networks
(
    consumer_id   text,
    priority      double,
    producer_id   text,
    primary key (consumer_id, priority, producer_id)
    ) with clustering order by (priority asc, producer_id asc);
