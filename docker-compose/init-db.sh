set -e

DB_NAMES="${DB_NAME},adotar_test_db"

IFS=',' read -ra DBS <<< "$DB_NAMES"

for db in "${DBS[@]}"; do
  echo "Creating database '$db'"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE $db;
EOSQL
done