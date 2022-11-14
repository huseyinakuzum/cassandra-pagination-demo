FROM cassandra:4.0.5@sha256:ee24b4893623c23d37425c26828be4fa464f3a46e842377511247b61636ede21

RUN echo "authorizer : CassandraAuthorizer" >> /etc/cassandra/cassandra.yaml
RUN echo "authenticator: PasswordAuthenticator" >> /etc/cassandra/cassandra.yaml

COPY ./init-scripts /cassandra-init-scripts/

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# entrypoint.sh wraps entrypoint of the cassandra docker image to run cql scripts under cassandra-init-scripts
ENTRYPOINT ["/entrypoint.sh"]
CMD ["cassandra", "-f"]