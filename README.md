# kd-tree-api

An API built over Java ML's KDTree implementation. Primarily designed as a service for my [weather-api](https://github.com/corinm/weather-api).

## API Documentation
### Create a new tree
**`POST /tree/create`**  
  * Header: `{ Content-Type: application/json }`  
  * Request body data: `[ { key1: 10, key2: 20, data: obj }, ... ]`  
    * The keys must be called key1 and key2.  
    * The values must be **numbers** (decimals and negatives are okay).  
    * Data can be any JavaScript object.  

Example body data:
```
[
  { key1: 36.065805, key2: -112.135609, data: { id: 'a1', name: 'Grand Canyon' } },
  { key1: 32.732219, key2: -117.149219, data: { id: 'a2', name: 'Balboa Park' } }
  ...
]
```
On success will return an id (integer) and secret (string) for the created tree, for querying it later
```
{
    id: 12345678910,
    secret: "9idjrri4v30imbelutpe0jbka1"
}
```


### Find closest match to a given key
**`POST /tree/search`**  
  * Header: `{ Content-Type: application/json }`  
  * Request body data: `{ id: 1, secret: 'aaa', keys: { key1: 10, key2: 20 } }`  
    * `id` and `secret` are returned from `POST /tree/create`
    * The keys must be called key1 and key2, in an object called key.  
    * The values must be **numbers** (decimals and negatives are okay).  

Example body data:
```
{
  id: 12345678910,
  secret: "9idjrri4v30imbelutpe0jbka1",
  key: {
    key1: 37.301600,
    key2: -112.945644
  }
}
```
Will return a JSON object containing the `data` attribute of the closest match
```
{
    id: 'a3',
    name: 'Zion National Park'
}
```

## Running Locally

1. Install: Java, Maven and Heroku Toolbelt  
2. Clone this repo: `git clone https://github.com/corinm/kd-tree-api`  
3. Install and set up [PostgreSQL](https://www.postgresql.org/)  
  * Create .env file containing `DATABASE_URL=postgresql://localhost:5432/test-db`  
  * Create new database in command line `createdb test-db`  
5. Run `cd kd-tree-api`  
6. Run `mvn clean install`  
7. Run `heroku local` to start the app  

## Deploying to Heroku

1. Run `heroku create`  
2. Run `git push heroku master`  
3. Run `heroku open`  

## Attributions
  * This application was built upon Heroku's [java-getting-started](https://github.com/heroku/java-getting-started.git) seed project.
  * The k-dimensional tree implementation used is from the [Java-ML library](http://java-ml.sourceforge.net/).
